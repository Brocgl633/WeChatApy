package com.cao.paymentdemo.controller;

import com.cao.paymentdemo.entity.Product;
import com.cao.paymentdemo.service.ProductService;
import com.cao.paymentdemo.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author : CGL
 * @Date : 2022/2/27 8:00
 * @Desc :
 */

@CrossOrigin    // 开放前端的跨域访问
@Api(tags = "商品管理")
@RestController // 所有的接口方法以json格式返回
@RequestMapping("/api/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public R test() {
        return R.ok().data("message", "hello").data("now", new Date());
    }

    @GetMapping("/list")
    public R list() {
        List<Product> list = productService.list();
        return R.ok().data("productList", list);
    }
}
