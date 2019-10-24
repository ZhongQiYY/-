package com.zhong.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhong.dao.IUserDao;
import com.zhong.domain.Role;
import com.zhong.domain.UserInfo;
import com.zhong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo info = iUserDao.findByUserName(username);

//        User user = new User(info.getUsername(), "{noop}"+info.getPassword(),getAuthority(info.getRoles()));

//        依次为用户名，密码，状态0为不可用账户1为可用账户，账户是否过期，认证是否过期，账户是否锁定，账户角色
        User user = new User(info.getUsername(), info.getPassword(),
                info.getStatus() == 0 ? false : true, true, true, true, getAuthority(info.getRoles()));

        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
//        在spring-security.xml文件中：access="访问系统的人，必须有ROLE_USER的角色"
//        拿到角色表中用户的角色，要有ROLE_USER或ROLE_ADMIN角色才能登入成功
        for(Role r : roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+r.getRoleName()));
        }
        return list;
    }

    /**
     * 查询出所有用户
     * @param page
     * @param size
     * @return
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<UserInfo> findAllUser(int page, int size) {
        PageHelper.startPage(page, size);
        return iUserDao.findAllUser();
    }

    /**
     * 保存用户
     * @param userInfo
     */
    @Override
    public void saveUser(UserInfo userInfo) {
//        对插入的密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        iUserDao.saveUser(userInfo);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public UserInfo findByUserId(String userId) {
        return iUserDao.findByUserId(userId);
    }

    /**
     * 通过用户id查询出该用户没有的角色
     * @param userId
     * @return
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Role> findOtherRoles(String userId) {
        return iUserDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for(String roleId : roleIds){
            iUserDao.addRoleToUser(userId,roleId);
        }
    }
}
