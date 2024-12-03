package com.greglixandrao.desafioapispringrailway.domain.repository;

import com.greglixandrao.desafioapispringrailway.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductName(String productName);

    Product findByProductName(String productName);
}
