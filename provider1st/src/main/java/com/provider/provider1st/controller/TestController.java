package com.provider.provider1st.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hanlex.Liu on 2019/11/5 16:58.
 * 功能描述 :
 */

@RestController
@RequestMapping("/test1")
@Slf4j
public class TestController {

    @GetMapping("test1")
    public Object test(){
        //测试fegin的超时时间  ribbon 是 0.5 +6 fegin 是4+4
        //结论超过1秒就会超时 readTimeout指的是处理程序时间+客户端接收到响应时间
        int index = (int) (Math.random() * (3));
        System.out.println("time"+index);
        try {
            TimeUnit.SECONDS.sleep(index);
        } catch (InterruptedException e) {
        }
        log.info("test1-info");
        log.error("test1-error");
        log.warn("test1-warn");
        log.debug("test1-debug");
        return "123456";
    }
}
