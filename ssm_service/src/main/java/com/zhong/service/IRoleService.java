package com.zhong.service;

import com.zhong.domain.Permission;
import com.zhong.domain.Role;

import java.util.List;

public interface IRoleService {
    /**
     * 查询出所有角色信息
     * @return
     */
    List<Role> findAllRole();

    /**
     * 保存角色
     * @param role
     */
    void saveRole(Role role);

    /**
     * 通过id查询角色信息
     * @param roleId
     * @return
     */
    Role findByRoleId(String roleId);

    /**
     * 通过角色id查询出该角色没有的资源权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermissions(String roleId);

    /**
     * 向角色添加资源权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds);
}













