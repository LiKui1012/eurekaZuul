package com.provider.provider3rd.controller;

import javax.annotation.Resource;

import com.provider.provider3rd.feignClient.Provider1Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hanlex.Liu on 2019/11/5 16:58.
 * 功能描述 :
 */

@RestController
@RequestMapping("/test3")
public class TestController {

    @Resource
    private Provider1Client provider1Client;//引入privider1对应的 feignClient

    @GetMapping("test3")
    public Object test(){
        return "789JQK";
    }

    @GetMapping("test4")
    public Object test4(){
        String provider1Str = provider1Client.test1(); //使用 feignClient 中的方法 请求 provider1 中的接口
        return "test4" + provider1Str;
    }

}
