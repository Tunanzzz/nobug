//package com.nobug.interceptor;
//
//import com.alibaba.fastjson.JSONObject;
//import com.auth0.jwt.exceptions.AlgorithmMismatchException;
//import com.auth0.jwt.exceptions.SignatureVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nobug.config.JWT;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Description: 拦截器
// * @Author: 新写的旧代码
// * @CreateTime: 2022/3/24
// */
//public class UserInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Map<String, Object> map = new HashMap<>();
//
//        String Token = request.getHeader("token");
//
//        // 捕获刚刚JWT中抛出的异常,并封装对应的返回信息
//        try {
//            JWT.verifyToken(Token);
//            return true;
//        }catch (SignatureVerificationException e){
//            map.put("msg", "无效签名");
//        }catch (TokenExpiredException e){
//            map.put("msg", "已过期");
//        }catch (AlgorithmMismatchException e){
//            map.put("msg", "算法不一致");
//        }catch (Exception e){
//            map.put("msg", "无效身份信息");
//        }
//        // 封装返回值
//        map.put("code", 4010);
//        JSONObject json = new JSONObject(map);
//        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.print(json);
//        writer.flush();
//        writer.close();
//        return false;
//    }
//}