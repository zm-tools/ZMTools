package com.zm.zmtools.controller;


import com.zm.zmtools.pojo.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/6  19:27
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/authc")
public class OrderController {

    /**
     * token的参数 要放在请求头中
     * @return
     */
    @PostMapping(value = "/video/play_record")
    public JsonData findMyPlayRecord(){
        //从数据库中查询出来
        Map<String ,String> recordMap = new HashMap<>();

        recordMap.put("拥有authc 角色 权限才能访问","我的天哪");
        recordMap.put("拥有authc 角色 权限才能访问","我的天哪");
        recordMap.put("拥有authc 角色 权限才能访问","我的天哪");

        return JsonData.buildSuccess(recordMap);

    }

}
