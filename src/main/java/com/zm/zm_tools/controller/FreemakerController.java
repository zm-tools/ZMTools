package com.zm.zm_tools.controller;

import com.zm.zm_tools.entity.TblUserInfo;
import com.zm.zm_tools.pojo.RespInfo;
import com.zm.zm_tools.pojo.ServerSetting;
import com.zm.zm_tools.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            respInfo.setCode(0).setMessage("查询成功").setData(userInfoList);
        }else{
            respInfo.setCode(1).setMessage("暂无数据").setData(null);
        }
        return respInfo;
    }

}
