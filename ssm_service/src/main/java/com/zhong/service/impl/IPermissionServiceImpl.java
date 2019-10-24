package com.zhong.service.impl;

import com.zhong.dao.IPermissionsDao;
import com.zhong.domain.Permission;
import com.zhong.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionsDao iPermissionsDao;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Permission> findAllPermission() {
        return iPermissionsDao.findAllPermission();
    }

    @Override
    public void savePermission(Permission permission) {
        iPermissionsDao.savePermission(permission);
    }
}
