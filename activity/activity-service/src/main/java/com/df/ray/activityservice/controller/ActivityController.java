package com.df.ray.activityservice.controller;

import com.df.ray.model.UserInfo;
import com.df.ray.userapi.IFeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ActivityController
 *
 * @author ray
 * @date 2021/11/30
 */
@RestController
public class ActivityController {

    @Autowired
    private IFeignUserService iFeignUserService;

    @GetMapping("act")
    public String getActivity() {
        return "我是activity服务";
    }

    @GetMapping("user")
    public UserInfo getActivityUser() {
        return iFeignUserService.getUserInfo();
    }
}
