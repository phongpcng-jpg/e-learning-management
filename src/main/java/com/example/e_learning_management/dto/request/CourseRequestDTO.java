package com.example.e_learning_management.dto.request;

import java.math.BigDecimal;

import com.example.e_learning_management.message.CourseMessage;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
public class CourseRequestDTO {

    @NotBlank(message = CourseMessage.COURSE_CODE_NOT_BLANK)
    @Pattern(
            regexp = "^KH-\\d{4}$",
            message = CourseMessage.COURSE_CODE_INVALID
    )
    private String courseCode;

    @NotBlank(message = CourseMessage.TITLE_NOT_BLANK)
    @Size(
            min = 10,
            max = 150,
            message = CourseMessage.TITLE_SIZE_INVALID
    )
    private String title;

    @Size(
            min = 1,
            message = CourseMessage.DESCRIPTION_INVALID
    )
    private String description;

    @NotNull(message = CourseMessage.PRICE_NOT_NULL)
    @PositiveOrZero(message = CourseMessage.PRICE_INVALID)
    private BigDecimal price;

    @NotNull(message = CourseMessage.DURATION_NOT_NULL)
    @Positive(message = CourseMessage.DURATION_INVALID)
    private Integer durationHours;

    @NotBlank(message = CourseMessage.INSTRUCTOR_EMAIL_NOT_BLANK)
    @Email(message = CourseMessage.INSTRUCTOR_EMAIL_INVALID)
    private String instructorEmail;

}
