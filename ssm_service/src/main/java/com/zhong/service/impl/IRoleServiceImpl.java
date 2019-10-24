package com.zhong.service.impl;

import com.zhong.dao.IRoleDao;
import com.zhong.domain.Permission;
import com.zhong.domain.Role;
import com.zhong.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao iRoleDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Role> findAllRole() {
        return iRoleDao.findAllRole();
    }

    @Override
    public void saveRole(Role role) {
        iRoleDao.saveRole(role);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Role findByRoleId(String roleId) {
        return iRoleDao.findByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Permission> findOtherPermissions(String roleId) {
        return iRoleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for(String permissionId : permissionIds){
            iRoleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}












