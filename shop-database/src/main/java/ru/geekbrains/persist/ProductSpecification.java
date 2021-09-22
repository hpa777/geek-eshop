package ru.geekbrains.persist;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class ProductSpecification {

    public static Specification<Product> minPrice(BigDecimal minPrice) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.ge(root.get("price"), minPrice));
    }
    public static Specification<Product> maxPrice(BigDecimal maxPrice) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.le(root.get("price"), maxPrice));
    }
    public static Specification<Product> byName(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }
    public static Specification<Product> byCategoryId(long categoryId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), categoryId));
    }

}
