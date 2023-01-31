package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.entities.PaymentDO;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caojing
 * @since 2023-01-04
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/list")
    public JsonData list() {
        List<PaymentDO> list = paymentService.list();
        return JsonData.buildSuccess(list);
    }

    @GetMapping("/getById/{id}")
    public JsonData getById(@PathVariable Long id) {
        PaymentDO paymentDO = paymentService.getById(id);
        if (paymentDO != null) {
            return JsonData.buildSuccess(paymentDO, "机器:" + serverPort);
        }
        return JsonData.buildError("暂无该记录");
    }

    @PostMapping("/add")
    public JsonData add(@RequestBody PaymentDO payment) {
        boolean save = paymentService.save(payment);
        if (save) {
            return JsonData.buildSuccess("机器:" + serverPort);
        }
        return JsonData.buildError("插入失败");
    }

}

