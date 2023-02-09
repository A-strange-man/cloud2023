package com.atguigu.springcloud.remote;

import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.entities.PaymentDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author CaoJing
 * @date 2023/02/09 22:02
 */
@Component
@FeignClient(name = "CLOUD-PAYMENT-SERVICE")
public interface PaymentServiceRemote {

    @GetMapping("/payment/getById/{id}")
    JsonData getById(@PathVariable("id") Long id);

    @PostMapping("/payment/add")
    JsonData add(@RequestBody PaymentDO payment);

}
