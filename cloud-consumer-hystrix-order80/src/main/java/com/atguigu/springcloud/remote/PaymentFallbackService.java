package com.atguigu.springcloud.remote;

import com.atguigu.springcloud.common.JsonData;
import com.atguigu.springcloud.remote.PaymentServiceRemote;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author CaoJing
 * @date 2023/02/14 18:27
 */
@Component
public class PaymentFallbackService implements PaymentServiceRemote {

    @Override
    public JsonData paymentOk(Integer id) {
        return JsonData.buildError("paymentInfoOk_fallback，系统异常");
    }

    @Override
    public JsonData paymentTimeout(Integer id) {
        return JsonData.buildError("paymentinfoTimeout_fallback,系统异常");
    }
}
