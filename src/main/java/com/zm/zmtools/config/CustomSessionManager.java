package com.zm.zmtools.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @ClassName CustomSessionManager
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/6  0:55
 * @Version 1.0
 */

@Slf4j
public class CustomSessionManager extends DefaultWebSessionManager {


    private static final String AUTHOR = "token";

    public CustomSessionManager() {
        super();
    }

    /**
     * 前后端分离 的代码
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHOR);
        System.out.println("sessionId --> "+sessionId);
        if (sessionId != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);

            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,
                    sessionId);

            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,
                    Boolean.TRUE);

            return sessionId;
        } else {
            return super.getSessionId(request, response);

        }

    }


}
