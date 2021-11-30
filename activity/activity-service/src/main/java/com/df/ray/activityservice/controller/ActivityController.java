package com.df.ray.activityservice.controller;

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


    @GetMapping("act")
    public String getActivity() {
        return "我是activity服务";
    }
}
