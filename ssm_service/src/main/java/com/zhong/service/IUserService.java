package com.zhong.service;

import com.zhong.domain.Role;
import com.zhong.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAllUser(int page, int size);

    void saveUser(UserInfo userInfo);

    UserInfo findByUserId(String userId);

    List<Role> findOtherRoles(String userId);

    void addRoleToUser(String userId, String[] roleIds);
}
