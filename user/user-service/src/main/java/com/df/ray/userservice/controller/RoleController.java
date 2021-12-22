package com.df.ray.userservice.controller;


import com.df.ray.userservice.common.model.ResultVO;
import com.df.ray.userservice.mapper.auto.RoleMapper;
import com.df.ray.userservice.model.auto.Role;
import com.df.ray.userservice.service.IRoleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/role")
@NoArgsConstructor
@AllArgsConstructor
public class RoleController {
    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IRoleService roleService;

    @GetMapping("listRole")
    public ResultVO<List<Role>> listRole() {
        List<Role> roles = roleMapper.selectList(null);
        return new ResultVO().success(roles);
    }

    @GetMapping("listRoleByIds")
    public ResultVO<List<Role>> getRoleById() {
        Collection<Role> roles1 = roleService.listByIds(Arrays.asList(1, 2, 3));
        return new ResultVO().success(roles1);
    }
}
