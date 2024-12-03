package com.greglixandrao.desafioapispringrailway.service.impl;

import com.greglixandrao.desafioapispringrailway.domain.model.Product;
import com.greglixandrao.desafioapispringrailway.domain.repository.ProductRepository;
import com.greglixandrao.desafioapispringrailway.service.ProductService;
import com.greglixandrao.desafioapispringrailway.service.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public Product findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Transactional
    public Product update(Long id, Product productToUpdate) {
        Product dbProduct = findById(id);
        if (!dbProduct.getId().equals(productToUpdate.getId())) {
            throw new BusinessException("Product id does not match");
        }

        dbProduct.setProductName(productToUpdate.getProductName());
        dbProduct.setPrice(productToUpdate.getPrice());
        dbProduct.setStock(productToUpdate.getStock());
        dbProduct.setDescription(productToUpdate.getDescription());
        dbProduct.setCategories(productToUpdate.getCategories());

        return productRepository.save(dbProduct);
    }

    @Transactional
    public Product create(Product productToCreate) {
        ofNullable(productToCreate).orElseThrow(() -> new BusinessException("Product cannot be null"));
        if (productRepository.existsByProductName(productToCreate.getProductName())) {
            throw new BusinessException("Product already exists");
        }
        return productRepository.save(productToCreate);
    }

    @Transactional
    public void delete(Long id) {
        Product dbProduct = this.findById(id);
        this.productRepository.delete(dbProduct);
    }
}
