package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.ListParams;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> findAll();

    Page<CategoryDto> findWithPagination(ListParams listParams);

    Optional<CategoryDto> findById(Long id);

    void save(CategoryDto categoryDto);

    void deleteById(Long Id);
}
