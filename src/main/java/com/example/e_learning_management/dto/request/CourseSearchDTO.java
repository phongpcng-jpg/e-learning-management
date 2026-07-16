package com.example.e_learning_management.dto.request;

import java.math.BigDecimal;

import com.example.e_learning_management.message.CourseMessage;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
public class CourseSearchDTO {
    
    private String courseCode;

    private String title;

    private String description;

    @PositiveOrZero(message = CourseMessage.PRICE_MIN_INVALID)
    private BigDecimal minPrice;

    @PositiveOrZero(message = CourseMessage.PRICE_MAX_INVALID)
    private BigDecimal maxPrice;
    
    @Positive(message = CourseMessage.DURATION_MIN_INVALID)
    private Integer minDurationHours;
    
    @Positive(message = CourseMessage.DURATION_MAX_INVALID)
    private Integer maxDurationHours;

    private String instructorEmail;

}
