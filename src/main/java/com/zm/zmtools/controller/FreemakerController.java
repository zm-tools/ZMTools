package com.zm.zmtools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zm.zmtools.config.ServerSetting;
import com.zm.zmtools.entity.TblUserInfo;
import com.zm.zmtools.pojo.RespInfo;
import com.zm.zmtools.service.TestService;

import java.util.List;

@Controller
@RequestMapping(value = "/free")
public class FreemakerController {

    @Autowired
    private ServerSetting serverSetting;

    @Autowired
    private TestService testService;

    @GetMapping(value = "hello")
    public String freeIndex(ModelMap model){
        model.addAttribute("setting",serverSetting);

        return "freemark/index";
    }

    @GetMapping(value = "hello2")
    public String thyIndex(ModelMap model){
        model.addAttribute("setting",serverSetting);
        return "index";
    }



    @PostMapping(value = "/getAllUserInfo")
    @ResponseBody
    public RespInfo getUserInfo(){
        RespInfo respInfo = new RespInfo();
        List<TblUserInfo> userInfoList = testService.findAllUserInfoService();
        if (userInfoList!=null && userInfoList.size()>0){
            respInfo.setCode(0);
            respInfo.setMessage("查询成功");
            respInfo.setData(userInfoList);
        }else{
            respInfo.setCode(1);
            respInfo.setMessage("暂无数据");
            respInfo.setData(null);
        }
        return respInfo;
    }

}
