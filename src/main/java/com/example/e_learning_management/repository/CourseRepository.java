package com.example.e_learning_management.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.e_learning_management.dto.response.CourseResponseDTO;
import com.example.e_learning_management.enity.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("""
        SELECT new com.example.e_learning_management.dto.response.CourseResponseDTO(
            c.id,
            c.courseCode,
            c.title,
            c.description,
            c.price,
            c.durationHours,
            c.instructorEmail
        )
        From Course c
        WHERE 
        (
            :courseCode IS NULL
            OR :courseCode = ''
            OR LOWER(c.courseCode) LIKE LOWER(CONCAT('%', :courseCode, '%'))
        ) AND (
            :title IS NULL
            OR :title = ''
            OR LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%'))
        ) AND (
            :description IS NULL
            OR :description = ''
            OR LOWER(c.description) LIKE LOWER(CONCAT('%', :description, '%'))
        ) AND (
            :minPrice IS NULL
            OR c.price >= :minPrice
        ) AND (
            :maxPrice IS NULL
            OR c.price <= :maxPrice
        ) AND (
            :minDurationHours IS NULL
            OR c.durationHours >= :minDurationHours
        ) AND (
            :maxDurationHours IS NULL
            OR c.durationHours <= :maxDurationHours
        ) AND (
            :instructorEmail IS NULL
            OR :instructorEmail = ''
            OR LOWER(c.instructorEmail) LIKE LOWER(CONCAT('%', :instructorEmail, '%'))
        )
    """)
    Page<CourseResponseDTO> search(
        @Param("courseCode") String courseCode,
        @Param("title") String title,
        @Param("description") String description,
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("minDurationHours") Integer minDurationHours,
        @Param("maxDurationHours") Integer maxDurationHours,
        @Param("instructorEmail") String instructorEmail,
        Pageable pageable
    );

}
