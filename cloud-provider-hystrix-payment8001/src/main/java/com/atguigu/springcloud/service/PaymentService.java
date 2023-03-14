package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

/**
 * @author CaoJing
 * @date 2023/02/11 22:47
 */
@Service
public class PaymentService {

    /**
     * 服务正常调用
     * @param id
     * @return
     */
    public String paymentInfoOK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_ok,id: " + id + "~~~";
    }

    /**
     * 服务降级
     */
    // 3s以内走正常业务逻辑，超过三秒则调paymentInfoTimeOutHandler方法
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "7000")
    })
    public String paymentInfoTimeOut(Integer id) {
        int timeNumber = 10;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_timeOut;id" + id + "耗时：" + timeNumber + "s";
    }

    // 服务降级调用的方法
    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfoTimeOutHandler;id" + id + "~~~~";
    }


    /**
     * 服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("***id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " 调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后再试  /(ToT)/~~" + id;
    }
}
