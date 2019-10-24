package com.zhong.dao;

import com.zhong.domain.Role;
import com.zhong.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserDao {

    /**
     * 通过用户名查询出对应的用户信息及角色信息用来进行用户登入
     * @param username
     * @return
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.zhong.dao.IRoleDao.findById"))
    })
    UserInfo findByUserName(String username);

    /**
     * 查询出所有用户信息
     * @return
     */
    @Select("select * from users")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.zhong.dao.IRoleDao.findById"))
    })
    List<UserInfo> findAllUser();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);

    /**
     *查询某个用户详情
     * @param userId
     * @return
     */
    @Select("select * from users where id = #{userId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.zhong.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUserId(String userId);

    /**
     * 查询出用户没有的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}













