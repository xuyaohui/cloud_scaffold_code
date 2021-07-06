package com.github.yaohui.admin;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.github.yaohui.*.mapper")
public class CloudScaffoldAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudScaffoldAdminApplication.class, args);
    }

}
