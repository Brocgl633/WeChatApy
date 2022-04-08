package com.cao.paymentdemo.service.impl;

import com.cao.paymentdemo.entity.Product;
import com.cao.paymentdemo.mapper.ProductMapper;
import com.cao.paymentdemo.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
