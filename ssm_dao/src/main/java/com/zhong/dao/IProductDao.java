package com.zhong.dao;

import com.zhong.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 产品dao
 */
public interface IProductDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from product")
     List<Product> findAll();

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id);

    /**
     * 保存数据
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
















