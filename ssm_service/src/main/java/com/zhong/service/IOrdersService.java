package com.zhong.service;

import com.zhong.domain.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> findAllOrders(int age, int size);


    Orders findById(String ordersId);
}
