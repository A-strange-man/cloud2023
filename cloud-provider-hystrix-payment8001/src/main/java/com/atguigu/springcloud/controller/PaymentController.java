package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CaoJing
 * @date 2023/02/11 22:51
 */
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/payment/hystrix/ok/{id}")
    public JsonData paymentOk(@PathVariable("id") Integer id) {
        String str = paymentService.paymentInfoOK(id);
        return JsonData.buildSuccess(str);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public JsonData paymentTimeout(@PathVariable("id") Integer id) {
        String str = paymentService.paymentInfoTimeOut(id);
        return JsonData.buildSuccess(str);
    }
}
