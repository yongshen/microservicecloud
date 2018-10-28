package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
public class DeptConsumer_Turbine_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(DeptConsumer_Turbine_App.class, args);
	}
}
