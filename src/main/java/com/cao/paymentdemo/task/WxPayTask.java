package com.cao.paymentdemo.task;

import com.cao.paymentdemo.entity.OrderInfo;
import com.cao.paymentdemo.service.OrderInfoService;
import com.cao.paymentdemo.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : CGL
 * @Date : 2022/3/5 11:33
 * @Desc :
 */

@Component
@Slf4j
public class WxPayTask {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private WxPayService wxPayService;

    /**
     * 秒 分 时 日 月 周
     * 以秒为例
     * *: 每秒都执行
     * 1-3: 从第1秒开始执行，到第3秒结束执行
     * 0/3: 从第0秒开始，每隔3秒执行1次
     * ?: 不指定
     * 日和周不能同时指定，指定其中之一，则另一个设置为？
     */
    @Scheduled(cron = "*/3 * * * * ?")
    public void task1() {
        log.info("task1 被执行 ......");
    }

    /**
     * 从第0秒开始每隔30秒执行1次，查询创建超过5分钟，并且未支付的订单
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void orderConfirmed() throws Exception {
        log.info("orderConfirmed 被执行 ......");

        List<OrderInfo> orderInfoList = orderInfoService.getNoPayOrderByDuration(5);

        for (OrderInfo orderInfo: orderInfoList) {
            String orderNo = orderInfo.getOrderNo();
            log.warn("超时订单 ---> {}", orderNo);

            // 核实订单状态：调用微信支付查单接口
            wxPayService.checkOrderStatus(orderNo);
        }
    }
}
