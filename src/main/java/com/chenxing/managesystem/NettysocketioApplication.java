package com.chenxing.managesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

/**
 * .为了适应 ie8，不使用vue的版本
 */

@EnableFeignClients
@SpringBootApplication
@MapperScan("com.chenxing.managesystem.mapper") // 将项目中对应的mapper类
@ImportResource(locations = { "classpath:activiti.cfg.xml" }) // 引入xml配置文件
public class NettysocketioApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettysocketioApplication.class, args);
	}
}
