package com.wheelseye.ehiring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PageDTO<T> {

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalCount;
    private List<T> data;

}
