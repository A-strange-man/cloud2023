package com.atguigu.springcloud.remote;

import com.atguigu.springcloud.common.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author CaoJing
 * @date 2023/02/11 23:11
 */
@Component
@FeignClient(name = "cloud-hystrix-payment-service", fallback = PaymentFallbackService.class)
public interface PaymentServiceRemote {

    @GetMapping("/payment/hystrix/ok/{id}")
    public JsonData paymentOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public JsonData paymentTimeout(@PathVariable("id") Integer id);

}
