package ru.geekbrains.controller;


import java.math.BigDecimal;


public class ProductListParams extends ListParams {

    private BigDecimal minPrice;

    private BigDecimal  maxPrice;

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }


}
