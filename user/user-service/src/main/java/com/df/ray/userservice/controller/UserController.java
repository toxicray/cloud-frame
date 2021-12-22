package com.df.ray.userservice.controller;

import com.df.ray.model.UserInfo;
import com.df.ray.userapi.IFeignUserService;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * UserController
 *
 * @author ray
 * @date 2021/11/17
 */
@RestController
public class UserController implements IFeignUserService {

    @GetMapping("getUser")
    @Override
    public UserInfo getUserInfo() {
//        if ( true ) {
//            throw new IllegalArgumentException("异常");
//        }
        UserInfo userInfo = new UserInfo();
        userInfo.setName("tyrion");
        userInfo.setValue("value");
        return userInfo;
    }
}
