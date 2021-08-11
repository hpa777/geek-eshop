package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.ProductListParams;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAll();

    Page<ProductDto> findWithFilter(ProductListParams productListParams);

    Optional<ProductDto> findById(Long id);

    void save(ProductDto productDto);

    void deleteById(Long Id);
}
