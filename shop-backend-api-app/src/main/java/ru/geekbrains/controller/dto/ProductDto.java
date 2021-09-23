package ru.geekbrains.controller.dto;


import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private CategoryDto category;

    private List<Long> pictures;

    private  Long firstPicture;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, BigDecimal price, CategoryDto category, List<Long> pictures) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.pictures = pictures;
        if (this.pictures.size() > 0) {
            firstPicture = this.pictures.get(0);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<Long> getPictures() {
        return pictures;
    }

    public void setPictures(List<Long> pictures) {
        this.pictures = pictures;
    }

    public Long getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(Long firstPicture) {
        this.firstPicture = firstPicture;
    }
}
