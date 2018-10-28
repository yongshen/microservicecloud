package com.atguigu.springcloud.command;

import com.atguigu.springcloud.entities.Dept;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @author 申光远
 * @create 2018-10-25 21:36
 * @desc 测试
 **/
public class DeptCommand extends HystrixCommand<Dept> {

    private RestTemplate restTemplate;
    private Long id;

    public DeptCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Dept run() throws Exception {
        return restTemplate.getForObject("http://MICROSERVICECLOUD-DEPT/dept/get/{1}", Dept.class, id);
    }
}
