package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.controller.ProductListParams;
import ru.geekbrains.persist.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(
                product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        mapCategoryDto(product))
                ).collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto> findWithFilter(ProductListParams productListParams) {
        Specification<Product> spec  = Specification.where(null);
        if (productListParams.getMinPrice() != null) {
            spec = spec.and(ProductSpecification.minPrice(productListParams.getMinPrice()));
        }
        if (productListParams.getMaxPrice() != null) {
            spec = spec.and(ProductSpecification.maxPrice(productListParams.getMaxPrice()));
        }

        Sort sort = Sort.by(Sort.Direction.fromString(Optional.ofNullable(productListParams.getSortDir()).filter(c->!c.isBlank()).orElse("ASC"))
                , Optional.ofNullable(productListParams.getSortBy()).filter(c->!c.isBlank()).orElse("id")
        );
        PageRequest pageRequest = PageRequest.of(Optional.ofNullable(productListParams.getPage()).orElse(1) - 1
                , Optional.ofNullable(productListParams.getPageSize()).orElse(3)
                , sort
        );
        return productRepository.findAll(spec, pageRequest)
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        mapCategoryDto(product)
                        ));
    }

    private static CategoryDto mapCategoryDto(Product product) {
        Category category = product.getCategory();
        if (category != null) {
            return new CategoryDto(
                    product.getCategory().getId(),
                    product.getCategory().getName()
            );
        }
        return null;
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        mapCategoryDto(product)
                        ));
    }

    @Override
    public void save(ProductDto productDto) {
        Category category = new Category();
        if (productDto.getCategory() != null) {
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
        }
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                category
        );
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long Id) {
        productRepository.deleteById(Id);
    }
}
