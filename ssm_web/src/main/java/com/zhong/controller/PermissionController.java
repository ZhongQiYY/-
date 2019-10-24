package com.zhong.controller;

import com.zhong.domain.Permission;
import com.zhong.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/findAllPermission.zq")
    public ModelAndView findAllPermission(){
        ModelAndView mv = new ModelAndView();
        List<Permission> allPermission = iPermissionService.findAllPermission();
        mv.addObject("permissionList", allPermission);
        mv.setViewName("permission-list");

        return mv;
    }

    @RequestMapping("/savePermission.zq")
    public String savePermission(Permission permission){
        iPermissionService.savePermission(permission);
        return "forward:/findAllPermission.zq";
    }
}

















