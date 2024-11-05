package com.product.inventory.inventory.core.service.domain;


import com.product.inventory.inventory.core.domain.Brand;
import com.product.inventory.inventory.core.domain.entity.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PriceTest is a test class for Price.
 * It uses the ExtendWith annotation to use SpringExtension which integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model.
 */
@ExtendWith(SpringExtension.class)
class PriceTest {

    /**
     * Test method for Price class.
     * It tests the validation of the Price's getters and setters, excluding the startDate and endDate,
     * the bean constructor, and the toString method excluding startDate and endDate.
     */
    @Test
    @DisplayName("Test Context Price")
    void test() {
        assertThat(Price.class,
                allOf(hasValidBeanConstructor(),
                        hasValidBeanToStringExcluding("startDate", "endDate"),
                        hasValidGettersAndSettersExcluding("startDate", "endDate"))
        );
    }

    @Test
    @DisplayName("Test All Args Constructor")
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Brand brand = new Brand(); // Reemplazar con una instancia real de Brand

        Price price = new Price(1L, brand, now, now, 1L, 1L, 1, 10.0, "USD");

        assertEquals(1L, price.getId());
        assertEquals(brand, price.getBrand());
        assertEquals(now, price.getStartDate());
        assertEquals(now, price.getEndDate());
        assertEquals(1L, price.getPriceList());
        assertEquals(1L, price.getProductId());
        assertEquals(1, price.getPriority());
        assertEquals(10.0, price.getPrice());
        assertEquals("USD", price.getCurr());
    }

    @Test
    @DisplayName("Test Builder")
    void testBuilder() {
        LocalDateTime now = LocalDateTime.now();
        Brand brand = new Brand(); // Reemplazar con una instancia real de Brand

        Price price = Price.builder()
                .id(1L)
                .brand(brand)
                .startDate(now)
                .endDate(now)
                .priceList(1L)
                .productId(1L)
                .priority(1)
                .price(10.0)
                .curr("USD")
                .build();

        assertEquals(1L, price.getId());
        assertEquals(brand, price.getBrand());
        assertEquals(now, price.getStartDate());
        assertEquals(now, price.getEndDate());
        assertEquals(1L, price.getPriceList());
        assertEquals(1L, price.getProductId());
        assertEquals(1, price.getPriority());
        assertEquals(10.0, price.getPrice());
        assertEquals("USD", price.getCurr());
    }
}

