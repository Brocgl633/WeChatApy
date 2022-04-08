package com.cao.paymentdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

// 此包后面的四个类均继承了这个类
// 并且还有@TableName，用来对应数据库的表名
// 并且实体类中的驼峰命名是对应的数据库表中的下划线分隔命名的，因为mybatis-plus自动转化对应
@Data
public class BaseEntity {

    //定义主键策略：跟随数据库的主键自增
    @TableId(value = "id", type = IdType.AUTO)
    private String id; //主键

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
}
