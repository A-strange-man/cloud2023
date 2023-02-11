package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.remote.PaymentServiceRemote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CaoJing
 * @date 2023/02/11 23:13
 */
@RestController
public class PaymentController {

    @Resource
    private PaymentServiceRemote paymentServiceRemote;

    @GetMapping("/order/hystrix/ok/{id}")
    public JsonData orderOk(@PathVariable("id") Integer id) {
        return paymentServiceRemote.paymentOk(id);
    }

    @GetMapping("/order/hystrix/timeout/{id}")
    public JsonData paymentTimeout(@PathVariable("id") Integer id) {
        return paymentServiceRemote.paymentTimeout(id);
    }

}
