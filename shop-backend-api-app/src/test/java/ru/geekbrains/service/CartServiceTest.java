package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.service.dto.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testIfNewCartIsEmpty() {
        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddProduct() {
        ProductDto ep = new ProductDto();
        ep.setId(1L);
        ep.setPrice(new BigDecimal(123));
        ep.setName("Product name");
        cartService.addProductQty(ep, "color", "material", 2);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1,lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals("color", lineItem.getColor());
        assertEquals("material", lineItem.getMaterial());
        assertEquals(2, lineItem.getQty());

        assertEquals(ep.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductDto());
        assertEquals(ep.getName(), lineItem.getProductDto().getName());
    }

    @Test
    public void testUpdateProductQty() {
        ProductDto ep = new ProductDto();
        ep.setId(1L);
        ep.setPrice(new BigDecimal(123));
        ep.setName("Product name");
        cartService.addProductQty(ep, "color", "material", 1);

        cartService.updateProductQty(ep, "color", "material", 2);
        List<LineItem> lineItems = cartService.getLineItems();
        assertEquals(2, lineItems.get(0).getQty());

        cartService.updateProductQty(ep, "color", "material", 0);
        lineItems = cartService.getLineItems();
        assertEquals(0, lineItems.size());
    }

    @Test
    public void testRemoveProduct(){
        ProductDto ep = new ProductDto();
        ep.setId(1L);
        ep.setPrice(new BigDecimal(123));
        ep.setName("Product name");
        cartService.addProductQty(ep, "color", "material", 1);

        cartService.removeProduct(ep, "color", "material");

        List<LineItem> lineItems = cartService.getLineItems();
        assertEquals(0, lineItems.size());
    }

    @Test
    public void testGetSubTotal(){
        BigDecimal sum = new BigDecimal(0);
        for (long i = 1; i < 10; i++) {
            ProductDto ep = new ProductDto();
            ep.setId(i);
            BigDecimal price = generateRandomBigDecimalFromRange(new BigDecimal(1), new BigDecimal(1000));
            ep.setPrice(price);
            ep.setName("Product name");
            int qty = getRandomNumber(1, 100);
            cartService.addProductQty(ep, "color", "material", qty);
            sum = sum.add(price.multiply(new BigDecimal(qty)));
        }
        assertEquals(sum, cartService.getSubTotal());
    }

    private static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
