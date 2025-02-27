package com.example.internship.controller;

import com.example.internship.common.Result;
import com.example.internship.service.UserService;
import com.example.internship.service.RoleService;
import com.example.internship.entity.BaseUser;
import com.example.internship.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public Result<List<BaseUser>> getUserList() {
        // 返回用户列表数据
        return Result.success(userService.list());
    }

    @GetMapping("/roles")
    public Result<List<Role>> getRoleList() {
        // 返回角色列表数据
        return Result.success(roleService.list());
    }

    // 其他API接口...
} 