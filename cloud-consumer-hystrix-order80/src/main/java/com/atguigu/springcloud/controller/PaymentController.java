package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.remote.PaymentServiceRemote;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public JsonData paymentTimeout(@PathVariable("id") Integer id) {
        return paymentServiceRemote.paymentTimeout(id);
    }

    public JsonData paymentInfoTimeOutHandler(@PathVariable("id") Integer id) {
        return JsonData.buildError("系统繁忙0_0");
    }

}
