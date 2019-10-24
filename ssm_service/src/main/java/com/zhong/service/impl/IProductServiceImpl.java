package com.zhong.service.impl;

import com.zhong.dao.IProductDao;
import com.zhong.domain.Product;
import com.zhong.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *产品service的实现类
 */
@Service
@Transactional
public class IProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    /**
     * 查询所有产品
     * @return
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Product> findAll(){
        return iProductDao.findAll();
    }

    /**
     * 保存数据
     * @param product
     */
    @Override
    public void save(Product product){
        iProductDao.save(product);
    }
}
