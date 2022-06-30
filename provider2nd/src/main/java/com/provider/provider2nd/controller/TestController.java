package com.provider.provider2nd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hanlex.Liu on 2019/11/5 16:58.
 * 功能描述 :
 */

@RestController
@RequestMapping("/test1")
public class TestController {

    @GetMapping("test1")
    public Object test(){
        return "456789";
    }

}
