package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 申光远
 * @create 2018-10-25 20:43
 * @desc 测试
 **/
@Service
public class DeptTestService {

    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
    @Autowired
    private RestTemplate restTemplate;

    public boolean add(Dept dept)
    {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

//    @HystrixCommand(fallbackMethod = "getCallback")

    //使用注解实现合并请求
    @HystrixCollapser(batchMethod = "getByIds", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")})
    public Dept get(Long id)
    {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    public Dept getCallback(Long id) {
        return new Dept().setDeptno(0l).setDname("该id：" + id + "获取失败，熔断信息").setDb_source("no database");
    }

    public List list()
    {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    @HystrixCommand
    public List getByIds(List<Long> idArr) {
        List<Dept> depts = restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
        List<Dept> list = new ArrayList<>();
        for(Long id : idArr) {
            for(Dept d : depts) {
                if(d.getDeptno().equals(id)) {
                    list.add(d);
                }
            }
        }
        return list;
    }
}
