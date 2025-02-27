package com.example.internship.service;

import com.example.internship.entity.BaseUser;
import java.util.List;

public interface UserService {
    boolean isUsernameExists(String username);
    BaseUser register(String username, String password, String role, String name);
    BaseUser login(String username, String password, String role);
    List<BaseUser> list();
} 