package com.zhong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 为WEB-INF下的jsp页面互相跳转提供桥梁
 */
@Controller
public class DispatcherController {
    /**
     * 点击首页跳转到main.jsp
     * @return
     */
    @RequestMapping("/shouYeTOMainJsp.zq")
    public String shouYeTOMainJsp(){
        return "main";
    }

    /**
     * 点击新建跳转到product-add.jsp
     * @return
     */
    @RequestMapping("/xinJianTOProductAddJsp.zq")
    public String xinJianTOProductAddJsp(){
        return "product-add";
    }

    /**
     * 点击跳转到user-add.jsp
     * @return
     */
    @RequestMapping("/xinJianTOUserAddJsp.zq")
    public String xinJianTOUserAddJsp(){
        return "user-add";
    }

    @RequestMapping("/xinJianTORoleAddJsp.zq")
    public String xinJianTORoleAddJsp(){
        return "role-add";
    }

    @RequestMapping("/xinJianTOPermissionAddJsp.zq")
    public String xinJianTOPermissionAddJsp(){
        return "permission-add";
    }
}















