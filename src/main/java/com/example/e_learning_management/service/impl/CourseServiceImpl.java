package com.example.e_learning_management.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.e_learning_management.dto.common.PageResponse;
import com.example.e_learning_management.dto.request.CourseRequestDTO;
import com.example.e_learning_management.dto.response.CourseResponseDTO;
import com.example.e_learning_management.enity.Course;
import com.example.e_learning_management.exception.ResourceNotFoundException;
import com.example.e_learning_management.message.CourseMessage;
import com.example.e_learning_management.repository.CourseRepository;
import com.example.e_learning_management.service.ICourseService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;

    @Override
    public PageResponse<CourseResponseDTO> getPagedCourses(
        int page, 
        int size, 
        String sortBy, 
        String direction
    ) {
        
        if (page < 0) {
            page = 0;
        }

        Sort sort = Sort.unsorted();

        if (
            sortBy != null && !sortBy.isBlank()
            && direction != null && !direction.isBlank()
        ) {

            sort = direction.equalsIgnoreCase("DESC")
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();

        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CourseResponseDTO> coursePage = 
            courseRepository.findAll(pageable).map(
                course -> CourseResponseDTO.builder()
                            .id(course.getId())
                            .courseCode(course.getCourseCode())
                            .title(course.getTitle())
                            .description(course.getDescription())
                            .price(course.getPrice())
                            .durationHours(course.getDurationHours())
                            .instructorEmail(course.getInstructorEmail())
                            .build()
            );

        return PageResponse.<CourseResponseDTO>builder()
                .items(coursePage.getContent())
                .page(coursePage.getNumber())
                .size(coursePage.getSize())
                .totalItems(coursePage.getTotalElements())
                .totalPages(coursePage.getTotalPages())
                .isLast(coursePage.isEmpty())
                .build();
        
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO request) {
        
        Course course = Course.builder()
                .id(null)
                .courseCode(request.getCourseCode())
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .durationHours(request.getDurationHours())
                .instructorEmail(request.getInstructorEmail())
                .build();

        Course savedCourse = courseRepository.save(course);

        return CourseResponseDTO.builder()
                .id(savedCourse.getId())
                .courseCode(savedCourse.getCourseCode())
                .title(savedCourse.getTitle())
                .description(savedCourse.getDescription())
                .price(savedCourse.getPrice())
                .durationHours(savedCourse.getDurationHours())
                .instructorEmail(savedCourse.getInstructorEmail())
                .build();

    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request) {
        
        Course existCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    CourseMessage.COURSE_NOT_FOUND
                ));

        existCourse.setCourseCode(request.getCourseCode());
        existCourse.setTitle(request.getTitle());
        existCourse.setDescription(request.getDescription());
        existCourse.setPrice(request.getPrice());
        existCourse.setDurationHours(request.getDurationHours());
        existCourse.setInstructorEmail(request.getInstructorEmail());

        Course savedCourse = courseRepository.save(existCourse);

        return CourseResponseDTO.builder()
                .id(savedCourse.getId())
                .courseCode(savedCourse.getCourseCode())
                .title(savedCourse.getTitle())
                .description(savedCourse.getDescription())
                .price(savedCourse.getPrice())
                .durationHours(savedCourse.getDurationHours())
                .instructorEmail(savedCourse.getInstructorEmail())
                .build();

    }

}
