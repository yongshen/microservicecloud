package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.ReactorConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 申光远
 * @create 2018-10-29 22:07
 * @desc
 **/
@RestController
@RequestMapping("/refactor")
public class RefactorDeptController_Consumer {

    @Autowired
    private ReactorConsumerService consumerService;

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        return consumerService.getById(id);
    }
}
