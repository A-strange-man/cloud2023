package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.entities.PaymentDO;
import com.atguigu.springcloud.service.PaymentService;
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

    @Resource
    private PaymentService paymentService;

    @GetMapping("/list")
    public JsonData list() {
        List<PaymentDO> list = paymentService.list();
        return JsonData.buildSuccess(list);
    }

    @PostMapping("/add")
    public JsonData add(@RequestBody PaymentDO payment) {
        boolean save = paymentService.save(payment);
        if (save) {
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("插入失败");
    }

}

