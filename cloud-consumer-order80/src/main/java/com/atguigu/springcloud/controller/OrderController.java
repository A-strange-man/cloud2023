package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.entities.PaymentDO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author CaoJing
 * @date 2023/01/04 19:10
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/add")
    public JsonData create(@RequestBody PaymentDO paymentDO) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", paymentDO, JsonData.class);
    }

    @GetMapping("/getById/{id}")
    public JsonData getById(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getById/" + id, JsonData.class);
    }

}
