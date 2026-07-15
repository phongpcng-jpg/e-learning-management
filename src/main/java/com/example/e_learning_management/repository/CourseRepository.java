package com.example.e_learning_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_learning_management.enity.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {

}
