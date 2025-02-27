package com.example.internship.repository;

import com.example.internship.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByOrderByFeaturedCountDesc();  // 按点击量降序
    List<Job> findAllByOrderByCreatedAtDesc();      // 按发布时间降序
    
    @Query("SELECT j FROM Job j WHERE " +
           "(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:location IS NULL OR j.location = :location) " +
           "AND (:jobType IS NULL OR j.jobType = :jobType) " +
           "AND (:education IS NULL OR j.education = :education)")
    Page<Job> searchJobs(
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("jobType") String jobType,
            @Param("education") String education,
            Pageable pageable
    );

    @Query("SELECT j.location as location, COUNT(j) as count FROM Job j WHERE " +
           "(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:jobType IS NULL OR j.jobType = :jobType) " +
           "AND (:education IS NULL OR j.education = :education) " +
           "GROUP BY j.location")
    List<Object[]> countByLocationWithKeyword(
            @Param("keyword") String keyword,
            @Param("jobType") String jobType,
            @Param("education") String education
    );

    @Query("SELECT j.jobType as jobType, COUNT(j) as count FROM Job j WHERE " +
           "(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:location IS NULL OR j.location = :location) " +
           "AND (:education IS NULL OR j.education = :education) " +
           "GROUP BY j.jobType")
    List<Object[]> countByJobTypeWithKeyword(
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("education") String education
    );

    @Query("SELECT j.education as education, COUNT(j) as count FROM Job j WHERE " +
           "(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:location IS NULL OR j.location = :location) " +
           "AND (:jobType IS NULL OR j.jobType = :jobType) " +
           "GROUP BY j.education")
    List<Object[]> countByEducationWithKeyword(
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("jobType") String jobType
    );

    default Map<String, Long> getLocationCounts(String keyword, String jobType, String education) {
        return countByLocationWithKeyword(keyword, jobType, education).stream()
            .collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (Long) row[1],
                (v1, v2) -> v1,
                LinkedHashMap::new
            ));
    }

    default Map<String, Long> getJobTypeCounts(String keyword, String location, String education) {
        return countByJobTypeWithKeyword(keyword, location, education).stream()
            .collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (Long) row[1],
                (v1, v2) -> v1,
                LinkedHashMap::new
            ));
    }

    default Map<String, Long> getEducationCounts(String keyword, String location, String jobType) {
        return countByEducationWithKeyword(keyword, location, jobType).stream()
            .collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (Long) row[1],
                (v1, v2) -> v1,
                LinkedHashMap::new
            ));
    }

    @Query("SELECT j FROM Job j WHERE " +
           "(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:location IS NULL OR j.location = :location) " +
           "AND (:jobType IS NULL OR j.jobType = :jobType) " +
           "AND (:education IS NULL OR j.education = :education) " +
           "ORDER BY " +
           "CASE WHEN :sortBy = 'salary' THEN " +
           "   CAST(REGEXP_SUBSTR(j.salary, '^[0-9]+') AS int) " +
           "WHEN :sortBy = 'latest' OR :sortBy IS NULL THEN j.createdAt " +
           "ELSE j.createdAt END DESC")
    Page<Job> searchJobsWithSort(
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("jobType") String jobType,
            @Param("education") String education,
            @Param("sortBy") String sortBy,
            Pageable pageable
    );
} 