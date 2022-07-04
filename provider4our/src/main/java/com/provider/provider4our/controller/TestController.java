package com.provider.provider4our.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hanlex.Liu on 2019/11/5 16:58.
 * 功能描述 :
 */

@RestController
@RequestMapping("/test4")
public class TestController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("getTest4")
    public Object test() {
        return "getTest4";
    }

    @GetMapping(value = "test4")
    public String getTest() {
        String restTemplateString = restTemplate.getForObject("http://provider1/test1/test1", String.class);
        System.out.println("getTest-restTemplate消费者调用提供者返回数据：" + restTemplateString);
        return "test4";
    }
}
