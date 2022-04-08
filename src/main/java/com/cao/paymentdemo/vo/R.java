package com.cao.paymentdemo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : CGL
 * @Date : 2022/2/27 8:35
 * @Desc :
 */

// 交给前端的响应的统一数据
@Data
@Accessors(chain = true)
public class R {

    private Integer code;   // 响应码
    private String message; // 响应信息
    private Map<String, Object> data = new HashMap<>(); // 实例化

    public static R ok() {
        R r = new R();
        r.setCode(0);
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(-1);
        r.setMessage("失败");
        return r;
    }

    // 通过data进行对前端的数据传输
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
