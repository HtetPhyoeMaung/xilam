package com.venzee.forXilam.repo;

import com.venzee.forXilam.entity.Category;
import com.venzee.forXilam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findByCategory(Category categoryId);
}
