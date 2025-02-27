package com.example.internship.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;           // 职位名称
    
    @Column(name = "company_name")
    private String companyName;     // 公司名称
    
    private String salary;          // 薪资
    private String location;        // 工作地点
    
    @Column(name = "job_type")
    private String jobType;         // 工作类型
    
    private String education;       // 学历要求
    private String description;     // 职位描述
    
    @Column(name = "featured_count")
    private Integer featuredCount = 0;  // 点击量，默认为0
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();    // 创建时自动设置当前时间
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 