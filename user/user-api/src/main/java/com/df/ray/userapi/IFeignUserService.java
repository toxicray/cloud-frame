package com.df.ray.userapi;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface IFeignUserService {


}
