package com.example.internship.controller;

import com.example.internship.entity.Job;
import com.example.internship.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecommendController {

    @Autowired
    private JobService jobService;

    @GetMapping("/recommend")
    public String recommend(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String jobType,
            @RequestParam(required = false) String education,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "latest") String sortBy,
            Model model
    ) {
        Page<Job> jobPage = jobService.searchJobs(keyword, location, jobType, education, page, sortBy);
        
        // 添加日志
        System.out.println("Total elements: " + jobPage.getTotalElements());
        System.out.println("Total pages: " + jobPage.getTotalPages());
        System.out.println("Current content size: " + jobPage.getContent().size());
        
        // 修改调用方式，移除current参数
        model.addAttribute("locationCounts", jobService.getLocationCounts(keyword, jobType, education));
        model.addAttribute("jobTypeCounts", jobService.getJobTypeCounts(keyword, location, education));
        model.addAttribute("educationCounts", jobService.getEducationCounts(keyword, location, jobType));
        
        model.addAttribute("jobs", jobPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jobPage.getTotalPages());
        model.addAttribute("totalItems", jobPage.getTotalElements());
        
        // 添加筛选条件到model，用于回显
        model.addAttribute("keyword", keyword);
        model.addAttribute("location", location);
        model.addAttribute("jobType", jobType);
        model.addAttribute("education", education);
        model.addAttribute("sortBy", sortBy);
        
        return "recommend";
    }
} 