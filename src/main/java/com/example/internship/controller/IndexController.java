package com.example.internship.controller;

import com.example.internship.entity.Job;
import com.example.internship.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private JobService jobService;  // 注入JobService

    @GetMapping({"/", "/index"})
    public String index(Model model, HttpSession session) {
        System.out.println("访问首页 - Session ID: " + session.getId());
        
        // 从session获取用户信息
        BaseUser user = (BaseUser) session.getAttribute("user");
        String role = (String) session.getAttribute("role");
        
        System.out.println("用户信息: " + (user != null ? user.getUsername() : "未登录"));
        System.out.println("角色信息: " + role);
        
        // 添加用户信息到model
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        
        // 获取并添加职位信息
        List<Job> featuredJobs = jobService.getFeaturedJobs();
        model.addAttribute("jobs", featuredJobs);
        
        return "index";
    }

    @GetMapping("/progress")
    public String progress() {
        return "progress";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
} 