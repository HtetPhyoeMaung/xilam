package com.venzee.forXilam.utils;

import com.venzee.forXilam.dto.CategoryDto;
import com.venzee.forXilam.dto.ProductDto;
import com.venzee.forXilam.dto.ResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class Utils {
    private Utils(){

    }
    public static ResponseDto buildResponseDtoForProduct(List<ProductDto> productDtoList, String message, HttpStatus status) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(status.value());
        responseDto.setMessage(message);
        responseDto.setProductList(productDtoList);
        return responseDto;
    }
    public static ResponseDto buildResponseDtoForCategory(List<CategoryDto> categoryDtoList, String message, HttpStatus status) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(status.value());
        responseDto.setMessage(message);
        responseDto.setCategoryList(categoryDtoList);
        return responseDto;
    }
}
