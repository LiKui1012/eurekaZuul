package com.provider.provider4our.controller;


import com.provider.provider4our.service.DataBaseUnionTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/testUnion")
@Slf4j
//这里测试引用databse数据源进行操作
public class DataBaseUnionTestController {

    @Resource
    private DataBaseUnionTestService dataBaseUnionTestService;

    @GetMapping("/find")
    public String findName(String id) {
        return dataBaseUnionTestService.findName(id);
    }
}
