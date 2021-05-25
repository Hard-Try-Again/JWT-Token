package com.ming.service.impl;

import com.ming.mapper.UserMapper;
import com.ming.pojo.User;
import com.ming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByNameAndPsd(String name, String psd) {


        return userMapper.findByNameAndPsd(name,psd);
    }
}
