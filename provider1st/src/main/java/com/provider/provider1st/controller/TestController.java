package com.provider.provider1st.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("test1-info");
        log.error("test1-error");
        log.warn("test1-warn");
        log.debug("test1-debug");
        return "123456";
    }
}
