package com.zm.zmtools.controller;


import com.zm.zmtools.pojo.JsonData;
import com.zm.zmtools.pojo.User;
import com.zm.zmtools.pojo.UserQuery;
import com.zm.zmtools.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName PublicController
 * @Description 测试使用
 * @Author zhaoluowei
 * @Date 2019/7/4  23:38
 * @Version 1.0
 */
@RestController
@RequestMapping(value = {"/pub", "/app"})
public class PublicController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findUserInfo")
    public User findUserInfo(@RequestParam("username") String username) {
        return userService.findAllUserInfoByUsername(username);
    }


    @RequestMapping(value = "/need_login")
    public JsonData needLogin() {
        JsonData jsonData = new JsonData();
        return JsonData.buildSuccess("温欣提示:请使用对应账号登录", -2);
    }


    @RequestMapping(value = "/not_permit")
    public JsonData notPermit() {
        JsonData jsonData = new JsonData();

        return JsonData.buildSuccess("没有权限拒绝访问", -3);
    }

    /**
     * get post 请求都能接受
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public JsonData index() {
        List<String> videoList = new ArrayList<>();
        videoList.add("请求都能接受 我不需要权限");
        videoList.add("请求都能接受 我不需要权限2");
        videoList.add("请求都能接受 我不需要权限3");
        videoList.add("请求都能接受 我不需要权限4");
        videoList.add("请求都能接受 我不需要权限5");

        return JsonData.buildSuccess(videoList);

    }

    /**
     * 只能接收 post 请求
     * 登录接口
     *
     * @param userQuery
     * @return
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonData longin(@RequestBody UserQuery userQuery, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("longin --> 我进来了");
        System.out.println(userQuery);
        Map<String, Object> map = new HashMap();
        try {
            Subject subject = SecurityUtils.getSubject();
            //拿到用户名密码之后进行 和数据库 中的用户名 密码信息进行比较。

            UsernamePasswordToken passwordToken = new UsernamePasswordToken(userQuery.getUsername(), userQuery.getPassword());
            subject.login(passwordToken);
            map.put("msg", "登陆成功");
            map.put("session_id", subject.getSession().getId());

            return JsonData.buildSuccess(map);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("账号或密码错误");
        }


    }


}
