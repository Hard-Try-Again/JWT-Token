package com.ming.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 从请求头中获取token
            String token = request.getParameter("token");

            Map<String, Object> map = new HashMap<>();
            map.put("code", -1);

            try {
                // 解析token并获取载荷
                Map<String, Object> claim = JwtUtils.getClaim(token);


                // 设置到域对象中
                request.setAttribute("claim", claim);

                return true;

                // 抛出对应的异常
            } catch (AlgorithmMismatchException e) {
                map.put("message", "算法不匹配");
            } catch (InvalidClaimException e) {
                map.put("message", "非法载荷");
            } catch (SignatureVerificationException e) {
                map.put("message", "签名不匹配");
            } catch (TokenExpiredException e) {
                map.put("message", "token已过期");
            } catch (Exception e) {
                map.put("message", "token异常，访问被终止");
            }

            // 获取Jackson核心处理对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 对象转Json字符串
            String json = objectMapper.writeValueAsString(map);

            // 设置响应内容类型
            response.setContentType("application/json; charset=UTF-8");

            // 发送响应
            response.getWriter().println(json);

            return false;
        }
    }
