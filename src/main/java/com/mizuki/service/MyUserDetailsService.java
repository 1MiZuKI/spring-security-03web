package com.mizuki.service;

import com.mizuki.dao.UserDao;
import com.mizuki.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setRoles(userDao.getRolesByUid(user.getId()));
        return user;
    }
}
