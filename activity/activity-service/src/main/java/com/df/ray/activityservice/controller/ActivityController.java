package com.df.ray.activityservice.controller;

import com.df.ray.model.UserInfo;
import com.df.ray.userapi.IFeignUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
//    @HystrixProperty(value = "3000", name = "metrics.rollingStats.timeInMilliseconds"),
//    @HystrixProperty(value = "2", name = "circuitBreaker.requestVolumeThreshold"),
//    @HystrixProperty(value = "50", name = "circuitBreaker.errorThresholdPercentage"),
//    @HystrixProperty(value = "5", name = "circuitBreaker.sleepWindowInMilliseconds"),
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(value = "2000", name = "execution.isolation.thread.timeoutInMilliseconds")
            },
            fallbackMethod = "firstFallBack"
    )
    public UserInfo getActivityUser() {
        return iFeignUserService.getUserInfo();
    }

    private UserInfo firstFallBack() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("张三");
        userInfo.setValue("嘿哈");
        return userInfo;
    }
}
