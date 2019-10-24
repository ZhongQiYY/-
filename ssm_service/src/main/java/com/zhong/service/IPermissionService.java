package com.zhong.service;

import com.zhong.domain.Permission;

import java.util.List;

public interface IPermissionService {

    /**
     * 查询所有资源权限
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 保存资源
     * @param permission
     */
    void savePermission(Permission permission);
}
