package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author CaoJing
 * @date 2023/02/06 18:27
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/consulInfo")
    public JsonData consulInfo() {
        String res = "spring cloud with consul: " + port + " " + UUID.randomUUID().toString();
        return JsonData.buildSuccess(res);
    }

}
