package com.ming;

import com.ming.utils.JwtUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class test {

    @Test
    public void testToken(){

        HashMap<String, Object> map = new HashMap<>();

        map.put("name","小名");
        map.put("age",12);
        //获取token
        String token = JwtUtils.getToken(map, 30);

        System.out.println(token);
        Map<String, Object> claim = JwtUtils.getClaim(token);

        System.out.println(claim);



    }

}
