package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 申光远
 * @create 2018-10-29 21:54
 * @desc
 **/
@RequestMapping("/refactor")
public interface DeptFeignService {

    @RequestMapping(value = "/getDept/{id}", method = RequestMethod.GET)
    public Dept getById(@PathVariable("id") Long id);

}
