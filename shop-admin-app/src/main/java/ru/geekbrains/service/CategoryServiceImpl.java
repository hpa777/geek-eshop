package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.ListParams;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(
                category -> new CategoryDto(
                        category.getId(),
                        category.getName()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public Page<CategoryDto> findWithPagination(ListParams listParams) {
        Sort sort = Sort.by(Sort.Direction.fromString(Optional.ofNullable(listParams.getSortDir()).filter(c->!c.isBlank()).orElse("ASC"))
                , Optional.ofNullable(listParams.getSortBy()).filter(c->!c.isBlank()).orElse("id")
        );
        PageRequest pageRequest = PageRequest.of(Optional.ofNullable(listParams.getPage()).orElse(1) - 1
                , Optional.ofNullable(listParams.getPageSize()).orElse(3)
                , sort
        );
        return categoryRepository.findAll(pageRequest)
                .map(category -> new CategoryDto(category.getId(), category.getName()));
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return categoryRepository.findById(id).map(
                category -> new CategoryDto(
                        category.getId(),
                        category.getName()
                )
        );
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = new Category(
                categoryDto.getId(),
                categoryDto.getName()
        );
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long Id) {
        categoryRepository.deleteById(Id);
    }
}
