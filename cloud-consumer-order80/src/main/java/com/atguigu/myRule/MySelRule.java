package com.atguigu.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaoJing
 * @date 2023/02/07 22:08
 */
@Configuration
public class MySelRule {

    // 随机
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }

}
