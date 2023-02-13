package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

/**
 * @author CaoJing
 * @date 2023/02/11 22:47
 */
@Service
public class PaymentService {

    public String paymentInfoOK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_ok,id: " + id + "~~~";
    }

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

}
