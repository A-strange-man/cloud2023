package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.entities.PaymentDO;
import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author CaoJing
 * @date 2023/01/04 19:10
 */
@RestController
@RequestMapping("/order")
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/add")
    public JsonData create(@RequestBody PaymentDO paymentDO) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", paymentDO, JsonData.class);
    }

//    @GetMapping("/getById/{id}")
//    public JsonData getById(@PathVariable Long id) {
//        return restTemplate.getForObject(PAYMENT_URL + "/payment/getById/" + id, JsonData.class);
//    }


    @GetMapping("/getById/{id}")
    public JsonData getById(@PathVariable Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return JsonData.buildError("无可用服务器");
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/getById/" + id, JsonData.class);
    }
}
