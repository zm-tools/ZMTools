package com.zm.zmtools.config;

import com.zm.zmtools.filter.CustomRolesAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description shiro访问全局的配置
 * @Author zhaoluowei
 * @Date 2019/7/6  0:13
 * @Version 1.0
 */

@Configuration
@Slf4j
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.info("ShiroFilterFactoryBean.shiroFilter() 执行");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        //必须设置  securityManager
        factoryBean.setSecurityManager(securityManager);

        //需要登录的接口
        //设置一些访问的规则 访问某个需要登录的界面但是用户没有登录 调用此接口。
        //接口路径名，   如果不是前后端分离 访问的是页面路径
        factoryBean.setLoginUrl("/pub/need_login");//这个写的是 登录的接口

        //登录成功后 跳转的URL 如果是前后端分离 调用这个没用
        factoryBean.setSuccessUrl("/");

        //登陆了但是没有权限 未授权调用此方法
        factoryBean.setUnauthorizedUrl("/pub/not_permit");

        //设置自定义filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("roleOrFilter", new CustomRolesAuthorizationFilter());
        factoryBean.setFilters(filterMap);


        //拦截器路径 linkedhashmap 可以保存存储有序
        //shiro中 有很多默认的过滤器 拦截器 加上之后进行 校验看是否符合这个规则决定是否通过
        Map<String, String> map = new LinkedHashMap<>();
        //退出过滤器
        map.put("/logout", "logout");
        //匿名可以访问 游客模式  anon 都是放行
        map.put("/pub/**", "anon");
        map.put("/app/**", "anon");

        //authc url定义的路径 必须通过认证才能进行访问
        //anon url可以匿名访问
        map.put("/authc/**", "authc");
        map.put("/admin/**", "roles[admin,root]");//必须同时满足才能访问
        map.put("/admin/**", "roleOrFilter[admin,root]");//自定义之后只要满足其中一个即可


        //有编辑权限才可以访问
        map.put("/video/update", "perms[video_update]");


        //authc  必须通过认证才能进行访问 通过账号密码 校验
        map.put("/**", "authc");

        factoryBean.setFilterChainDefinitionMap(map);


        return factoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //如果不是前后端分离 就不要进行设置。 session要自己进行管理
        securityManager.setSessionManager(sessionManager());

        //设置缓存
        securityManager.setCacheManager(redisCacheManager());

        //设置自定义 realm  securityManager才能知道 去那进行校验
        securityManager.setRealm(customRealm());//注解的形式 默认是方法名。作为实例的id
        return securityManager;
    }


    /**
     * 前后端分离的项目使用
     * 管理session的过去时间
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        //session 过期时间 单位是毫秒
        sessionManager.setGlobalSessionTimeout(300000);

        //设置session持久化的位置
        sessionManager.setSessionDAO(redisSessionDAO());


        return sessionManager;
    }

    /**
     * 自定义realm
     *
     * @return
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(credentialsMatcher());


        return customRealm;
    }

    /**
     * 设置一个加解密的规则 告诉securityManager 使用的是什么算法
     * 密码加密规则
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //散列算法
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(6);

        return credentialsMatcher;
    }


    /**
     * 配置Redis
     *
     * @return
     */
    @Bean
    public RedisManager getRedisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("106.13.102.104");
        redisManager.setPort(5379);
        redisManager.setPassword("zlw666");
        return redisManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置过期时间
        redisCacheManager.setExpire(60 * 5);
        return redisCacheManager;
    }


    //sessionDao 实现session持久化

    /**
     * session 持久化
     * 需要设置到 sessionmanager 中
     *
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        //设置 自定义sessionId 设置sessionId 生成器
        redisSessionDAO.setSessionIdGenerator(new CustomSessionIdGenerator());
        return redisSessionDAO;
    }

    /**
     * shiro的 生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }


//    AuthorizationAttributeSourceAdvisor
//    作用：加入注解的使用，不加入这个AOP注解不生效(shiro的注解 例如 @RequiresGuest)
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor()
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new
                DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }


}
