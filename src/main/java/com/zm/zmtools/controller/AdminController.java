package com.zm.zmtools.controller;


import com.zm.zmtools.pojo.JsonData;
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
@RequestMapping(value = "/admin")
public class AdminController {


    @RequestMapping("/video/order")
    public JsonData findMyPlayRecord(){

        Map<String ,String> recordMap = new HashMap<>();

        recordMap.put("拥有admin权限才能访问","我的天哪");
        recordMap.put("拥有admin权限才能访问","我的天哪");
        recordMap.put("拥有admin权限才能访问","我的天哪");

        return JsonData.buildSuccess(recordMap);

    }

}
