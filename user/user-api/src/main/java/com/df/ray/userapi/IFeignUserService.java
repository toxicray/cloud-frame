package com.df.ray.userapi;

import com.df.ray.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface IFeignUserService {

    @GetMapping("/user/getUser")
    UserInfo getUserInfo();
}
