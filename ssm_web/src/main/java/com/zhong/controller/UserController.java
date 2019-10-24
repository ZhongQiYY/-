package com.zhong.controller;

import com.github.pagehelper.PageInfo;
import com.zhong.domain.Role;
import com.zhong.domain.UserInfo;
import com.zhong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 查询所有用户
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAllUser.zq")
    public ModelAndView findAllUser(Integer page, Integer size){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> allUser = iUserService.findAllUser(page,size);
        PageInfo pageInfo = new PageInfo(allUser);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 保存用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/saveUser.zq")
    public String saveUser(UserInfo userInfo){
        iUserService.saveUser(userInfo);
        return "forward:/findAllUser.zq?page=1&size=4";
    }

    /**
     * 根据id查询用户详情
     * @param userId
     * @return
     */
    @RequestMapping("/findByUserId.zq")
    public ModelAndView findByUserId(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv = new ModelAndView();
        UserInfo user = iUserService.findByUserId(userId);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 根据id查询出用户可以添加的角色及用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.zq")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = iUserService.findByUserId(userId);
        List<Role> otherRoles = iUserService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");

        return mv;
    }

    /**
     * 给用户添加角色
     */
    @RequestMapping("/addRoleToUser.zq")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        iUserService.addRoleToUser(userId,roleIds);
        return "forward:/findUserByIdAndAllRole.zq?id="+userId;
    }
}












