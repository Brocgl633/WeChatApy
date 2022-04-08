package com.cao.paymentdemo.controller;

import com.cao.paymentdemo.entity.OrderInfo;
import com.cao.paymentdemo.enums.OrderStatus;
import com.cao.paymentdemo.service.OrderInfoService;
import com.cao.paymentdemo.vo.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : CGL
 * @Date : 2022/3/3 13:29
 * @Desc :
 */

@CrossOrigin    // 跨域
@RestController
@RequestMapping("/api/order-info")
@Api(tags = "商品订单管理")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/list")
    public R list() {
        List<OrderInfo> list = orderInfoService.listOrderByCreateTimeDesc();
        return R.ok().data("list", list);
    }

    /**
     * 查询本地订单状态
     * @param orderNo
     * @return
     */
    @GetMapping("query-order-status/{orderNo}")
    public R queryOrderStatus(@PathVariable String orderNo) {
        String orderStatus = orderInfoService.getOrderStatus(orderNo);
        if (OrderStatus.SUCCESS.getType().equals(orderStatus)) {
            return R.ok().setMessage("支付成功");
        }

        return R.ok().setCode(101).setMessage("支付中...");    // 定义101为支付失败，前端对其进行判断
    }
}
