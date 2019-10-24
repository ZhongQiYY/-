package com.zhong.dao;

import com.zhong.domain.Member;
import com.zhong.domain.Orders;
import com.zhong.domain.Product;
import com.zhong.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.zhong.dao.IProductDao.findById")),
    })
    List<Orders> findAllOrders();

    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.zhong.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId",javaType = Member.class,one = @One(select = "com.zhong.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = List.class,many = @Many(select = "com.zhong.dao.ITravellerDao.findByOrdersId")),
    })
    Orders findById(String ordersId);
}
