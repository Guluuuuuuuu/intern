package com.example.internship.service.impl;

import com.example.internship.entity.*;
import com.example.internship.repository.*;
import com.example.internship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public boolean isUsernameExists(String username) {
        return adminRepository.findByUsername(username).isPresent() ||
               studentRepository.findByUsername(username).isPresent() ||
               companyRepository.findByUsername(username).isPresent() ||
               schoolRepository.findByUsername(username).isPresent();
    }

    @Override
    public BaseUser register(String username, String password, String role, String name) {
        if (isUsernameExists(username)) {
            throw new RuntimeException("用户名已存在");
        }

        if ("admin".equals(role)) {
            throw new RuntimeException("不允许注册管理员账号");
        }
        
        String defaultAvatar = "/images/default-avatar.png";
        
        switch (role) {
            case "student":
                Student student = new Student();
                student.setUsername(username);
                student.setPassword(password);
                student.setName(name);
                student.setImage(defaultAvatar);
                return studentRepository.save(student);
                
            case "company":
                Company company = new Company();
                company.setUsername(username);
                company.setPassword(password);
                company.setName(name);
                company.setImage(defaultAvatar);
                return companyRepository.save(company);
                
            case "school":
                School school = new School();
                school.setUsername(username);
                school.setPassword(password);
                school.setName(name);
                school.setImage(defaultAvatar);
                return schoolRepository.save(school);
                
            default:
                throw new RuntimeException("无效的角色类型");
        }
    }

    @Override
    public BaseUser login(String username, String password, String role) {
        BaseUser user;
        
        try {
            // 特殊处理root用户
            if ("root".equals(username) && "admin".equals(role)) {
                Admin admin = new Admin();
                admin.setUsername("root");
                admin.setPassword("root");  // 实际应用中应该使用加密密码
                admin.setName("超级管理员");
                return admin;
            }

            switch (role) {
                case "admin":
                    user = adminRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("用户不存在"));
                    break;
                case "student":
                    user = studentRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("用户不存在"));
                    break;
                case "company":
                    user = companyRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("用户不存在"));
                    break;
                case "school":
                    user = schoolRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("用户不存在"));
                    break;
                default:
                    throw new RuntimeException("无效的角色类型");
            }

            if (!password.equals(user.getPassword())) {
                throw new RuntimeException("密码错误");
            }

            return user;
        } catch (Exception e) {
            System.out.println("用户验证失败：" + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<BaseUser> list() {
        List<BaseUser> allUsers = new ArrayList<>();
        allUsers.addAll(adminRepository.findAll());
        allUsers.addAll(studentRepository.findAll());
        allUsers.addAll(companyRepository.findAll());
        allUsers.addAll(schoolRepository.findAll());
        return allUsers;
    }
} 