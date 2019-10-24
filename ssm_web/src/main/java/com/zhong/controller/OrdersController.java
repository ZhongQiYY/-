package com.zhong.controller;

import com.github.pagehelper.PageInfo;
import com.zhong.domain.Orders;
import com.zhong.service.IOrdersService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private IOrdersService iOrdersService;

    /**
     * 分页查询所有订单信息
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAllOrders.zq")
    public ModelAndView findAllOrders(Integer page, Integer size){
        ModelAndView mv = new ModelAndView();
        List<Orders> allOrders = iOrdersService.findAllOrders(page,size);
//        pageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(allOrders);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }


    /**
     * 根据id查询订单详情
     * @param
     * @return
     */
    @RequestMapping("/findByIdOrders.zq")
    public ModelAndView findByIdOrders(@RequestParam(name = "id", required = true) String ordersId){
        ModelAndView mv = new ModelAndView();
        Orders orders = iOrdersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

}














