package com.zm.zmtools.controller;


import com.zm.zmtools.pojo.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/6  19:27
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/video")
public class VideoController {


    @RequestMapping("/update")
    public JsonData updateVideo(){

        return JsonData.buildSuccess("video更新成功");

    }

}
