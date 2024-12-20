package com.venzee.forXilam.service;

import com.venzee.forXilam.dto.CategoryDto;
import com.venzee.forXilam.dto.RequestDtoForCategory;
import com.venzee.forXilam.dto.ResponseDto;
import com.venzee.forXilam.entity.Category;
import com.venzee.forXilam.entity.Product;
import com.venzee.forXilam.exception.AlreadyExistException;
import com.venzee.forXilam.exception.CustomNotFoundException;
import com.venzee.forXilam.repo.CategoryRepository;
import com.venzee.forXilam.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;



    public ResponseEntity<ResponseDto> categories() {
        List<CategoryDto> categoryDtoList = categoryRepository.findAll().stream().map(this::mapToCategoryDto).toList();
        ResponseDto responseDto =Utils.buildResponseDtoForCategory(categoryDtoList,"There are all categories from venzee's server!", HttpStatus.OK);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    private CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public ResponseEntity<ResponseDto> createCategory(RequestDtoForCategory requestDtoForCategory) {
       Category checkCategory = categoryRepository.findByName(requestDtoForCategory.getName());
       if (checkCategory!=null){
           throw new AlreadyExistException("Category's already exist by "+requestDtoForCategory.getName());
       }
       Category category = new Category();
       category.setName(requestDtoForCategory.getName());
       categoryRepository.save(category);
       List<CategoryDto> categoryDtoList = new LinkedList<>();
       categoryDtoList.add(mapToCategoryDto(category));
       ResponseDto responseDto =Utils.buildResponseDtoForCategory(categoryDtoList,"Created Successfully.",HttpStatus.CREATED);
       return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    public Category findById(int category) {
        return categoryRepository.findById(category).orElseThrow(()->
                new CustomNotFoundException("Category not found by "+category));
    }
}
