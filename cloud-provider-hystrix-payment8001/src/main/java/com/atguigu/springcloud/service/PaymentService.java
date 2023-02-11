package com.atguigu.springcloud.service;

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

    public String paymentInfoTimeOut(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_timeOut;id" + id + "耗时：" + timeNumber + "s";
    }

}
