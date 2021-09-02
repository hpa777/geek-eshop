package ru.geekbrains.persist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {


    //@Override
    //@Query(value = "select p from Product p left join fetch p.category",
     //       countQuery = "select count (p) from Product p"
    //)
    @EntityGraph("product-with-category")
    Page<Product> findAll(Pageable pageable);
}
