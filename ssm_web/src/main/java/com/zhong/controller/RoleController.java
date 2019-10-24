package com.zhong.controller;

import com.zhong.domain.Permission;
import com.zhong.domain.Role;
import com.zhong.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    /**
     * 查询所有角色信息
     * @return
     */
    @RequestMapping("/findAllRole.zq")
    public ModelAndView findAllRole(){
        ModelAndView mv = new ModelAndView();
        List<Role> allRole = iRoleService.findAllRole();
        mv.addObject("roleList",allRole);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/saveRole.zq")
    public String saveRole(Role role){
        iRoleService.saveRole(role);
        return "forward:/findAllRole.zq";
    }

    /**
     * 根据角色id查询出对应角色，及其没有的资源
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.zq")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId){
        ModelAndView mv = new ModelAndView();
        Role role = iRoleService.findByRoleId(roleId);
        List<Permission> otherPermissions = iRoleService.findOtherPermissions(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermissions);
        mv.setViewName("role-permission-add");

        return mv;
    }

    @RequestMapping("/addPermissionToRole.zq")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids", required = true) String[] permissionIds){
        iRoleService.addPermissionToRole(roleId,permissionIds);
        return "forward:/findRoleByIdAndAllPermission.zq?id="+roleId;
    }
}

















