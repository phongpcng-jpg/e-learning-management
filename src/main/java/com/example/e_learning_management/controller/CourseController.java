package com.example.e_learning_management.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_learning_management.dto.common.ApiResponse;
import com.example.e_learning_management.dto.common.PageResponse;
import com.example.e_learning_management.dto.request.CourseRequestDTO;
import com.example.e_learning_management.dto.request.CourseSearchDTO;
import com.example.e_learning_management.dto.response.CourseResponseDTO;
import com.example.e_learning_management.message.CourseMessage;
import com.example.e_learning_management.service.ICourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponseDTO>> createCourse(
        @Valid @RequestBody CourseRequestDTO request
    ) {

        CourseResponseDTO response = courseService.createCourse(request);

        return ResponseEntity.ok(
            ApiResponse.success(
                Map.of("created", CourseMessage.CREATED),
                response
            )
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> updateCourse(
        @PathVariable("id") Long id,
        @Valid @ RequestBody CourseRequestDTO request
    ) {

        CourseResponseDTO response = courseService.updateCourse(id, request);

        return ResponseEntity.ok(
            ApiResponse.success(
                Map.of("updated", CourseMessage.UPDATED),
                response
            )
        );

    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseDTO>>> getPagedCourses(
                
        @RequestParam(name = "page", defaultValue = "0")
        int page,

        @RequestParam(name = "size", defaultValue = "10")
        int size,

        @RequestParam(name = "sortBy", required = false)
        String sortBy,

        @RequestParam(name = "direction", required = false)
        String direction

    ) {

        PageResponse<CourseResponseDTO> response =
            courseService.getPagedCourses(page, size, sortBy, direction);

        return ResponseEntity.ok(
            ApiResponse.success(
                Map.of("getPage", CourseMessage.GET_PAGE),
                response
            )
        );

    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseDTO>>> searchPagedCourse(
                
        @RequestParam(name = "page", defaultValue = "0")
        int page,

        @RequestParam(name = "size", defaultValue = "10")
        int size,

        @RequestParam(name = "sortBy", required = false)
        String sortBy,

        @RequestParam(name = "direction", required = false)
        String direction,

        @Valid @ModelAttribute CourseSearchDTO courseSearch

    ) {

        PageResponse<CourseResponseDTO> response = 
            courseService.searchPagedCourses(
                page, 
                size, 
                sortBy, 
                direction, 
                courseSearch
            );

        return ResponseEntity.ok(
            ApiResponse.success(
                Map.of("searchPage", CourseMessage.SEARCH_PAGE),
                response
            )
        );

    }

}
