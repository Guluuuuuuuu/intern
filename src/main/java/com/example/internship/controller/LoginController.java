package com.example.internship.controller;

import com.example.internship.entity.BaseUser;
import com.example.internship.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String role,
                       HttpSession session,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        try {
            System.out.println("开始登录处理: " + username + ", 角色: " + role);
            BaseUser user = userService.login(username, password, role);
            if (user != null) {
                System.out.println("登录成功，用户: " + username);
                session.setAttribute("user", user);
                session.setAttribute("role", role);
                session.setAttribute("username", username);
                session.setAttribute("isLoggedIn", true);
                
                // 添加成功消息
                redirectAttributes.addFlashAttribute("success", "登录成功");
                // 添加用户信息到model
                model.addAttribute("user", user);
                
                return "redirect:/";
            } else {
                redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
                return "redirect:/login";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/redirect")
    public String redirect(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        model.addAttribute("redirectUrl", "http://localhost:8080/" + 
            (role != null ? role : "student"));
        return "redirect";  // 这是一个新的模板页面
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String confirmPassword,
                         @RequestParam String role,
                         @RequestParam String name,
                         Model model) {
        try {
            // 验证输入
            if (username == null || username.trim().isEmpty()) {
                throw new RuntimeException("用户名不能为空");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new RuntimeException("密码不能为空");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new RuntimeException("姓名/名称不能为空");
            }
            if (!password.equals(confirmPassword)) {
                throw new RuntimeException("两次输入的密码不一致");
            }

            // 注册用户
            userService.register(username, password, role, name);
            model.addAttribute("success", "注册成功，请登录");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
} 