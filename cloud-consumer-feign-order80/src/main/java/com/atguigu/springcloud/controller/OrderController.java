package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.remote.PaymentServiceRemote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CaoJing
 * @date 2023/02/09 22:05
 */
@RestController
@RequestMapping("/orderFeign")
public class OrderController {

    @Resource
    private PaymentServiceRemote paymentService;

    @GetMapping("/getById/{id}")
    public JsonData getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

}
