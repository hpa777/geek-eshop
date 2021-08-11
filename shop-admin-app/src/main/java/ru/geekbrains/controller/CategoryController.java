package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.service.CategoryDto;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductDto;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategory(Model model, ListParams listParams) {
        model.addAttribute("categories", categoryService.findWithPagination(listParams));
        return "categories";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "category_form";
    }

    @GetMapping("/edit")
    public String editCategory(@RequestParam String id, Model model) {
        model.addAttribute("category", categoryService.findById(Long.parseLong(id))
                .orElseThrow(()-> new NotFoundException("Product not found"))
        );
        return "category_form";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam String id) {
        categoryService.deleteById(Long.parseLong(id));
        return "redirect:/category";
    }

    @PostMapping
    public String updateCategory(@Valid  @ModelAttribute("category") CategoryDto category, BindingResult result) {
        if (result.hasErrors()) {
            return "category_form";
        }
        categoryService.save(category);
        return "redirect:/category";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

}
