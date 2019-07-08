package com.zm.zmtools.controller;

import com.zm.zmtools.pojo.AddUserInfo;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.zm.zmtools.common.SSHUtils;
import com.zm.zmtools.config.DruidConfig;
import com.zm.zmtools.config.ServerSetting;
import com.zm.zmtools.entity.TblUser;
import com.zm.zmtools.entity.TblUserInfo;
import com.zm.zmtools.pojo.RespInfo;
import com.zm.zmtools.service.TestService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

    @Autowired
    private ServerSetting serverSetting;

    @Autowired
    private TestService testService;
    
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = "")
    public String needLogin(Model model) {
    	model.addAttribute("error", "登录");
    	return "user/login";
    }
    
    //登陆验证
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String index(HttpServletRequest request,Model model) {
    	TblUser user = new TblUser();
    	user.setUsername(request.getParameter("username"));
    	user.setPassword(request.getParameter("password"));
        if (user.getUsername() != null && user.getPassword() != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), "login");
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证开始");
            try {
                currentUser.login( token );
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    logger.info("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    System.out.println("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    System.out.println(currentUser.getSession().getId());
                    return "redirect:/free/hello";
                } else {
                    token.clear();
                    System.out.println("用户[" + user.getUsername() + "]登录认证失败,重新登陆");
                    return "redirect:/login";
                }
            } catch ( UnknownAccountException uae ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-username wasn't in the system");
                System.out.println("用户[" + user.getUsername() + "]登录认证失败,没有此用户");
                model.addAttribute("error", "没有此用户");
                return "user/login";
            } catch ( IncorrectCredentialsException ice ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-password didn't match");
                model.addAttribute("error", "密码错误");
                return "user/login";
            } catch ( LockedAccountException lae ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-account is locked in the system");
                model.addAttribute("error", "用户被锁定");
                return "user/login";
            } catch ( AuthenticationException ae ) {
                logger.error(ae.getMessage());
            }
        }
        return "login";
    }
}
