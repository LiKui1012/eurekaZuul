package com.db.datasharding.controller;

import com.alibaba.fastjson.JSON;
import com.db.datasharding.model.UserAuthEntity;
import com.db.datasharding.repository.UserAuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserAuthController {
    @Autowired
    private UserAuthDao userAuthDao;

    @PostMapping("/save")
    public String save() {
        for (int i = 0; i < 40; i++) {
            UserAuthEntity userAuthEntity = new UserAuthEntity();
            userAuthEntity.setUserId((long) i);
            userAuthEntity.setAddDate(new Date());
            userAuthEntity.setEmail("test" + i + "@163.com");
            userAuthEntity.setPassword("123456");
            userAuthEntity.setPhone((long) i);
            Random r = new Random();
            userAuthEntity.setRemark("" + r.nextInt(100));
            userAuthDao.save(userAuthEntity);
            System.out.println(
                    "数据入库" + i
            );
        }

        return "新增批量success";
    }

    @PostMapping("/select")
    public String select() {
        System.out.println("数据查询");
        List<UserAuthEntity> userAuthEntitys = userAuthDao.findAll(Sort.by(Sort.Order.desc("remark")));
        System.out.println("查询大小=" + userAuthEntitys.size());
        return JSON.toJSONString(userAuthDao.findAll(Sort.by(Sort.Order.desc("remark"))));
    }

    @PostMapping("/delte")
    public String delte() {
        System.out.println("数据删除");
        userAuthDao.deleteAll();
        return "删除成功";
    }

//    @PostMapping("/add")
//    public String add() {
//        UserAuthEntity userAuthEntity = new UserAuthEntity();
//        userAuthEntity.setAddDate(new Date());
//        Random r = new Random();
//        userAuthEntity.setEmail(r.nextInt(100)+"新增");
//        userAuthEntity.setPassword("2");
//        userAuthEntity.setPhone(r.nextInt(100)*(long)200);
//        userAuthEntity.setRemark("" + r.nextInt(100));
//        userAuthDao.save(userAuthEntity);
//        return "新增单个success";
//    };
}


