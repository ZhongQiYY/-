package com.zhong.service;

import com.zhong.domain.Product;

import java.util.List;

/**
 * 产品的业务代码
 */
public interface IProductService {
    /**
     * 查询所有
     * @return
     */
    List<Product> findAll();

    /**
     * 保存数据
     * @param product
     */
    void save(Product product);
}
