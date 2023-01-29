package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author CaoJing
 * @date 2023/01/29 20:48
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

}
