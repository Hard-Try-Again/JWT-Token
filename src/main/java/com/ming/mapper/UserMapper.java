package com.ming.mapper;

import com.ming.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findByNameAndPsd(@Param("username") String name, @Param("pwd") String psd);


}
