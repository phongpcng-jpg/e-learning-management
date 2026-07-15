package com.example.e_learning_management.dto.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> items;

    private Integer page;

    private Integer size;

    private Long totalItems;

    private Integer totalPages;

    private Boolean isLast;

}
