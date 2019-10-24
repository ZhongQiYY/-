package com.zhong.dao;

import com.zhong.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionsDao {
    /**
     * 查询某个角色的所有资源权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findPermissionByRoleId(String roleId);

    /**
     * 查询所有资源权限
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAllPermission();

    /**
     * 保存资源
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);
}














