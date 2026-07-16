package com.example.e_learning_management.service;

import com.example.e_learning_management.dto.common.PageResponse;
import com.example.e_learning_management.dto.request.CourseRequestDTO;
import com.example.e_learning_management.dto.request.CourseSearchDTO;
import com.example.e_learning_management.dto.response.CourseResponseDTO;


public interface ICourseService {

    PageResponse<CourseResponseDTO> getPagedCourses(
        int page,
        int size,
        String sortBy,
        String direction
    );

    CourseResponseDTO createCourse(
        CourseRequestDTO request
    );

    CourseResponseDTO updateCourse(
        Long id,
        CourseRequestDTO request
    );

    PageResponse<CourseResponseDTO> searchPagedCourses(
        int page,
        int size,
        String sortBy,
        String direction,
        CourseSearchDTO courseSearch
    );

}
