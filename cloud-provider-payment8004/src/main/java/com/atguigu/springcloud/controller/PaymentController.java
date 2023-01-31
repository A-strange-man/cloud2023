package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author CaoJing
 * @date 2023/01/31 18:59
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/zk")
    public JsonData paymentZK() {
        return JsonData.buildSuccess("springcloud with zookeeper: " + serverPort + "  " + UUID.randomUUID().toString());
    }

}
