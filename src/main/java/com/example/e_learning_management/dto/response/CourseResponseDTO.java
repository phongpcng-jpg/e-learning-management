package com.example.e_learning_management.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {
    
    private Long id;
    
    private String courseCode;
    
    private String title;
    
    private String description;
    
    private BigDecimal price;
    
    private Integer durationHours;
    
    private String instructorEmail;

}
