package com.venzee.forXilam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private int status;
    private String message;
    private LocalDateTime timeStamp;
    private List<ProductDto> productList;
    private List<CategoryDto> categoryList;
}
