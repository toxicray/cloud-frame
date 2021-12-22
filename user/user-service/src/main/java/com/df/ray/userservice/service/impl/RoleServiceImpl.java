package com.df.ray.userservice.service.impl;

import com.df.ray.userservice.model.auto.Role;
import com.df.ray.userservice.mapper.auto.RoleMapper;
import com.df.ray.userservice.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
