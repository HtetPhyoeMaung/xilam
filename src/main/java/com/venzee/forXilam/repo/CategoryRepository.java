package com.venzee.forXilam.repo;

import com.venzee.forXilam.entity.Category;
import com.venzee.forXilam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findByName(String name);
}
