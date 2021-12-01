package com.df.ray.userservice.controller;

import com.df.ray.model.UserInfo;
import com.df.ray.userapi.IFeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        UserInfo userInfo = new UserInfo();
        userInfo.setName("tyrion");
        userInfo.setValue("value");
        return userInfo;
    }
}
