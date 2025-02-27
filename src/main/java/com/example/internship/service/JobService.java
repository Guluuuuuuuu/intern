package com.example.internship.service;

import com.example.internship.entity.Job;
import com.example.internship.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class JobService {
    
    @Autowired
    private JobRepository jobRepository;

    public Map<String, Long> getLocationCounts(String keyword, String jobType, String education) {
        // 处理空字符串
        keyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword : null;
        jobType = (jobType != null && !jobType.trim().isEmpty()) ? jobType : null;
        education = (education != null && !education.trim().isEmpty()) ? education : null;
        
        return jobRepository.getLocationCounts(keyword, jobType, education);
    }

    public Map<String, Long> getJobTypeCounts(String keyword, String location, String education) {
        // 处理空字符串
        keyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword : null;
        location = (location != null && !location.trim().isEmpty()) ? location : null;
        education = (education != null && !education.trim().isEmpty()) ? education : null;
        
        return jobRepository.getJobTypeCounts(keyword, location, education);
    }

    public Map<String, Long> getEducationCounts(String keyword, String location, String jobType) {
        // 处理空字符串
        keyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword : null;
        location = (location != null && !location.trim().isEmpty()) ? location : null;
        jobType = (jobType != null && !jobType.trim().isEmpty()) ? jobType : null;
        
        return jobRepository.getEducationCounts(keyword, location, jobType);
    }

    public Page<Job> searchJobs(String keyword, String location, String jobType, String education, int page, String sortBy) {
        int pageSize = 10;
        
        // 使用新的排序查询方法
        Pageable pageable = PageRequest.of(page, pageSize);
        return jobRepository.searchJobsWithSort(
            keyword != null && !keyword.trim().isEmpty() ? keyword : null,
            location != null && !location.trim().isEmpty() ? location : null,
            jobType != null && !jobType.trim().isEmpty() ? jobType : null,
            education != null && !education.trim().isEmpty() ? education : null,
            sortBy,
            pageable
        );
    }

    @Transactional
    public void incrementFeaturedCount(Long jobId) {
        jobRepository.findById(jobId).ifPresent(job -> {
            job.setFeaturedCount(job.getFeaturedCount() + 1);
            jobRepository.save(job);
        });
    }
    
    public List<Job> getFeaturedJobs() {
        // 改为按点击量获取热门职位
        return jobRepository.findAllByOrderByFeaturedCountDesc();
    }
} 