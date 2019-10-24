package com.zhong.controller;

import com.zhong.domain.Product;
import com.zhong.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.zq")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList",ps);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 保存数据
     * @param product
     */
    @RequestMapping("/save.zq")
    public String save(Product product){
        productService.save(product);
        return "forward:/findAll.zq";
    }
}












