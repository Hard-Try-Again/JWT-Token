package com.ming.service;

import com.ming.pojo.User;

public interface UserService {

    User findByNameAndPsd(String name, String psd);
}
