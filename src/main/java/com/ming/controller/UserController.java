package com.ming.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ming.pojo.User;
import com.ming.service.UserService;
import com.ming.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        @RequestMapping("/login")
        @ResponseBody
        public Map<String,Object> login(User user){


            HashMap<String, Object> map = new HashMap<>();

//            map.put("code",-1);
//            map.put("message","登录失败");

            User result = userService.findByNameAndPsd(user.getName(), user.getPsd());

            if (result == null) {
                map.put("code", -1);
                map.put("message", "当前账号或者密码错误");
                map.put("data", null);
                // 登录成功
            } else {
                map.put("code", 200);
                map.put("message", "登录成功");

                Map<String, Object> claim = new HashMap<>();

                claim.put("name", result.getName());

                // 创建一个token
                String token = JwtUtils.getToken(claim, 30);

                map.put("token", token);
            }

            return map;
        }

        @ResponseBody
        @RequestMapping("/get")
        public Map<String,Object> get(HttpServletRequest request){

            HashMap<String, Object> map = new HashMap<>();

                map.put("message", "允许访问");
                map.put("code", 200);
                map.put("data", request.getAttribute("claim"));


            return map;
        }

}
