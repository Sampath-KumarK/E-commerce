package com.Ecommerce.Shoping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Shoping.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}