package com.zm.zmtools.controller;

import com.zm.zmtools.pojo.AddUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.zm.zmtools.config.ServerSetting;
import com.zm.zmtools.entity.TblUserInfo;
import com.zm.zmtools.pojo.RespInfo;
import com.zm.zmtools.service.TestService;

import java.util.List;

@Controller
@RequestMapping(value = "/free")
@Slf4j
public class FreemakerController {

    @Autowired
    private ServerSetting serverSetting;

    @Autowired
    private TestService testService;

    @GetMapping(value = "hello")
    public String freeIndex(ModelMap model) {
        model.addAttribute("setting", serverSetting);

        return "freemark/index";
    }

    @GetMapping(value = "hello2")
    public String thyIndex(ModelMap model) {
        model.addAttribute("setting", serverSetting);
        return "index";
    }


    @PostMapping(value = "/getAllUserInfo")
    @ResponseBody
    public RespInfo getUserInfo() throws Exception {
        RespInfo respInfo = new RespInfo();
        List<TblUserInfo> userInfoList = testService.findAllUserInfoService();
        if (userInfoList != null && userInfoList.size() > 0) {
            respInfo.setCode(0);
            respInfo.setMessage("查询成功");
            respInfo.setData(userInfoList);
        } else {
            respInfo.setCode(1);
            respInfo.setMessage("暂无数据");
            respInfo.setData(null);
        }
        return respInfo;
    }

    @PostMapping(value = "/addUserInfo",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RespInfo addUserInfo(@RequestBody AddUserInfo userInfo) {
        log.info("请求参数 --> "+userInfo);
        RespInfo respInfo = new RespInfo();
        try {
            int update = this.testService.addUserInfoService(userInfo);
            if (update > 0) {
                respInfo.setCode(0);
                respInfo.setMessage("插入成功");
                respInfo.setData(null);
            } else {
                respInfo.setCode(0);
                respInfo.setMessage("插入失败");
                respInfo.setData(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            respInfo.setCode(0);
            respInfo.setMessage("插入失败");
            respInfo.setData(null);
        }
        return respInfo;
    }


}
