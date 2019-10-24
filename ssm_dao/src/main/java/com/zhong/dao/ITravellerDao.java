package com.zhong.dao;

import com.zhong.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    /**
     * 根据订单id查询出该订单对应的所有旅客信息
     * @param ordersId
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    List<Traveller> findByOrdersId(String ordersId);
}
