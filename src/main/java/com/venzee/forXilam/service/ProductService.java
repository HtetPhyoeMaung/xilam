package com.venzee.forXilam.service;

import com.venzee.forXilam.dto.CategoryDto;
import com.venzee.forXilam.dto.ProductDto;
import com.venzee.forXilam.dto.RequestDtoForProduct;
import com.venzee.forXilam.dto.ResponseDto;
import com.venzee.forXilam.entity.Category;
import com.venzee.forXilam.entity.Product;
import com.venzee.forXilam.exception.AlreadyExistException;
import com.venzee.forXilam.exception.CustomNotFoundException;
import com.venzee.forXilam.repo.ProductRepository;
import com.venzee.forXilam.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public ResponseEntity<ResponseDto> getAllProducts() {
        List<ProductDto> productDtoList = productRepository.findAll().stream().
                map(this::mapToProductDto).toList();
        ResponseDto responseDto = Utils.buildResponseDtoForProduct(productDtoList,"There are all products from venzee's server!",HttpStatus.OK);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



    private ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        Category category =categoryService.findById(product.getCategory().getId());
        productDto.setCategoryDto(mapToCategoryDto(category));
        return productDto;
    }

    private CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    public ResponseEntity<ResponseDto> createProduct(int categoryId,RequestDtoForProduct requestDtoForProduct) {
        Category category = categoryService.findById(categoryId);
       Optional<Product> checkProduct = productRepository.findByCategory(category);
       if (checkProduct.isPresent()){
           throw new AlreadyExistException("Product's already exist by "+ categoryId);
       }
        Product product = new Product();
       product.setPrice(requestDtoForProduct.getPrice());
       product.setName(requestDtoForProduct.getName());
       product.setCategory(categoryService.findById(categoryId));
       productRepository.save(product);
       List<ProductDto> productDtoList = new LinkedList<>();
       productDtoList.add(mapToProductDto(product));
      ResponseDto responseDto = Utils.buildResponseDtoForProduct(productDtoList,"Updated Successfully",HttpStatus.CREATED);
      return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseDto> updateProduct(int productId, RequestDtoForProduct requestDtoForProduct) {
        Product product = productRepository.findById(productId).orElseThrow(()->
                new CustomNotFoundException("Product not found by "+productId));
        product.setName(requestDtoForProduct.getName());
        product.setPrice(requestDtoForProduct.getPrice());
        productRepository.save(product);
        List<ProductDto> productDtoList = new LinkedList<>();
        productDtoList.add(mapToProductDto(product));
        ResponseDto responseDto= Utils.buildResponseDtoForProduct(productDtoList,"Updated Successfully.",HttpStatus.OK);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> deleteProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(()->
                new CustomNotFoundException("Product not found by "+productId));
        productRepository.delete(product);
        List<ProductDto> productDtoList = new LinkedList<>();
        productDtoList.add(mapToProductDto(product));
        ResponseDto responseDto = Utils.buildResponseDtoForProduct(productDtoList,"Deleted Successfully.",HttpStatus.OK);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
