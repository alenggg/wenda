package com.nowcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SettingController {

    @RequestMapping(value = "/setting")
    @ResponseBody
    public String setting(@RequestParam(value = "key", required = false) String admin){
        if (admin.equals("admin")){
            return "setting is OK";
        }else {
            return "no setting";
        }
    }
}
