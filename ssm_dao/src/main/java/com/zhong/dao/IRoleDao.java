package com.zhong.dao;

import com.zhong.domain.Permission;
import com.zhong.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据id查询出角色（数据库中的角色信息）
     * @param id
     * @return
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{id})")
    List<Role> findById(String id);

    /**
     * 根据用户id查询出所有角色详细信息(javabean中封装的所有信息，除包含的所有用户)
     * @return
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.zhong.dao.IPermissionsDao.findPermissionByRoleId")),
    })
    List<Role> findRoleByUserId(String id);

    /**
     * 查询出所有角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAllRole();

    /**
     * 保存角色
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role);

    /**
     * 通过id查询角色
     * @param roleId
     * @return
     */
    @Select("select * from role where id=#{roleId}")
    Role findByRoleId(String roleId);

    /**
     * 通过id查询出该角色没有的资源权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    /**
     * 向角色添加资源权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}














