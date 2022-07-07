package com.provider.provider4our.service.imp;

import com.db.database.model.Test;
import com.db.database.repository.TestRepository;
import com.provider.provider4our.service.DataBaseUnionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DataBaseUnionTestServiceImp implements DataBaseUnionTestService {

//    @Autowired
    @Resource
    private  TestRepository testRepository;
    public String findName(String id){
        System.out.println("serviceImp接收"+id);
        Long testId = Long.valueOf(id);
        Test test = testRepository.findDistinctFirstById(testId);
        return test.getName() + "---" + test.getId();
    }



}
