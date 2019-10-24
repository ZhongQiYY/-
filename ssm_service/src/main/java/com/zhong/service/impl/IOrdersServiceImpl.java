package com.zhong.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhong.dao.IOrdersDao;
import com.zhong.domain.Orders;
import com.zhong.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Orders> findAllOrders(int age, int size) {
//        参数pageNum是页面值，参数pageSize代表是每页显示条数，必须在查询代码的前一句
        PageHelper.startPage(age,size);
        return iOrdersDao.findAllOrders();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Orders findById(String ordersId) {
        return iOrdersDao.findById(ordersId);
    }
}
