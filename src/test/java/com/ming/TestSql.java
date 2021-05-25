package com.ming;

import com.ming.pojo.User;
import com.ming.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = "classpath:application.xml")
public class TestSql {

    @Autowired
    private UserService userService;

    @Test
    public void sql(){

//        System.out.println(userService);
        User admin = userService.findByNameAndPsd("admin", "123");

        System.out.println(admin);


    }

}
