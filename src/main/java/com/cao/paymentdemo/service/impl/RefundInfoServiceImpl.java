package com.cao.paymentdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.paymentdemo.entity.OrderInfo;
import com.cao.paymentdemo.entity.RefundInfo;
import com.cao.paymentdemo.mapper.RefundInfoMapper;
import com.cao.paymentdemo.service.OrderInfoService;
import com.cao.paymentdemo.service.RefundInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cao.paymentdemo.util.OrderNoUtils;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo> implements RefundInfoService {

    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 根据订单号创建退款订单
     * @param orderNo
     * @param reason
     */
    @Override
    public RefundInfo createRefundByOrderNo(String orderNo, String reason) {

        // 根据订单号获取订单信息
        OrderInfo orderInfo = orderInfoService.getOrderByOrderNo(orderNo);

        // 根据订单号生成退款订单
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOrderNo(orderNo);                         // 订单号
        refundInfo.setRefundNo(OrderNoUtils.getRefundNo());     // 退款单编号
        refundInfo.setTotalFee(orderInfo.getTotalFee());        // 原订单金额
        refundInfo.setRefund(orderInfo.getTotalFee());          // 退款金额
        refundInfo.setReason(reason);                           // 退款原因

        // 保存退款订单
        baseMapper.insert(refundInfo);

        return refundInfo;
    }

    /**
     * 记录退款信息
     * @param content
     */
    @Override
    public void updateRefund(String content) {
        // 将json字符串转换成Map
        Gson gson = new Gson();
        Map<String, String> resultMap = gson.fromJson(content, HashMap.class);

        // 根据退款单编号修改退款单
        QueryWrapper<RefundInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("refund_no", resultMap.get("out_refund_no"));

        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setRefundId(resultMap.get("refund_id"));             // 微信支付退款单号

        // 查询退款和申请退款的返回参数
        if (resultMap.get("status") != null) {
            refundInfo.setRefundStatus(resultMap.get("status"));        // 退款中状态
            refundInfo.setContentReturn(content);                       // 将全部响应结果存入数据库的content字段
        }

        // 退款回调中的回调参数
        if (resultMap.get("refund_status") != null) {
            refundInfo.setRefundStatus(resultMap.get("refund_status"));  // 退款结果状态
            refundInfo.setContentNotify(content);                        // 将全部响应结果存入数据库的content字段
        }

        // 更新退款单
        baseMapper.update(refundInfo, queryWrapper);
    }
}
