package com.atguigu.springcloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author 申光远
 * @create 2018-10-29 22:04
 * @desc
 **/
@FeignClient(value = "MICROSERVICECLOUD-DEPT")
public interface ReactorConsumerService extends DeptFeignService {
}
