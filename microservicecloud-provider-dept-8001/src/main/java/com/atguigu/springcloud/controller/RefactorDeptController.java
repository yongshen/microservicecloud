package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptFeignService;
import com.atguigu.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 申光远
 * @create 2018-10-29 21:50
 * @desc
 **/
@RestController
public class RefactorDeptController implements DeptFeignService {

    @Autowired
    private DeptService service;

    @Override
    public Dept getById(@PathVariable("id") Long id) {
        return service.get(id);
    }
}
