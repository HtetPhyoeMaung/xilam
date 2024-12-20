package com.venzee.forXilam.controller;

import com.venzee.forXilam.dto.RequestDtoForCategory;
import com.venzee.forXilam.dto.RequestDtoForProduct;
import com.venzee.forXilam.dto.ResponseDto;
import com.venzee.forXilam.service.CategoryService;
import com.venzee.forXilam.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venzee")
@RequiredArgsConstructor
public class Controller {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<ResponseDto> getAllProducts(){
       return productService.getAllProducts();
    }

    @PostMapping("/product/{category_id}")
    public ResponseEntity<ResponseDto> createProduct(@PathVariable(value = "category_id")int categoryId,@RequestBody RequestDtoForProduct requestDtoForProduct){
        return productService.createProduct(categoryId,requestDtoForProduct);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable(value = "id")int productId, @RequestBody RequestDtoForProduct requestDtoForProduct){
        return productService.updateProduct(productId, requestDtoForProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable(value = "id")int productId){
        return productService.deleteProduct(productId);
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDto> categories(){
        return categoryService.categories();
    }

    @PostMapping("/category")
    public ResponseEntity<ResponseDto> createCategory(@RequestBody RequestDtoForCategory requestDtoForCategory){
        return categoryService.createCategory(requestDtoForCategory);
    }
}
