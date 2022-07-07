package com.db.datasharding.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.db.database.model.Test;
import com.db.database.repository.TestRepository;
import com.db.database.utils.BeanConvertorUtils;
import com.db.database.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/union")
@Slf4j
public class TestController {
    @Autowired(required = false)
    private TestRepository testRepository;

    @GetMapping("/find")
    public String findName(String id) {
        log.info("查询传参id={}", id);
        Long testId = Long.valueOf(id);
        //分表不支持findDistinctFirstById 因为sharding不支持union union all distinct
        //分表的主键策略怎么弄
        Test test = testRepository.getOne(testId);
        return test.getName() + "---" + test.getId();
    }

    @PostMapping("/addOrUpdate")
    public TestVO addOrUpdate(@RequestBody TestVO testVO) {
        log.info("新增修改传参={}", JSON.toJSONString(testVO));
        Test test = BeanConvertorUtils.toModel(testVO, Test.class);
        log.info("入库参数={}", JSON.toJSONString(test));
        Test testDb = testRepository.saveAndFlush(test);
        log.info("入库保存参数={}", JSON.toJSONString(testDb));
        TestVO testBack = BeanConvertorUtils.toVO(testDb, TestVO.class);
        log.info("返回参数={}", JSON.toJSONString(testBack));
        return testBack;
    }

    @DeleteMapping(value = "/delete")
    public void delete(@NotNull(message = "删除id不能为空") @RequestParam(value = "id") Long id) {
        log.info("删除参数={}", JSONUtils.toJSONString(id));
        boolean b = testRepository.existsById(id);
        log.info("id={},存在={}", id, b);
        testRepository.deleteById(id);
    }

}
