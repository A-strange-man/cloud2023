package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author CaoJing
 * @date 2023/02/06 14:38
 */
@RestController
@RequestMapping("/orderZK")
public class OrderZKController {

    private static final String INVOKE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/paymentInfo")
    public JsonData paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", JsonData.class);
    }

}
