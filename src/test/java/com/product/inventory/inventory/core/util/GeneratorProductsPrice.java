package com.product.inventory.inventory.core.util;


import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;

import java.time.LocalDateTime;

/**
 * GeneratorProductsPrice is a utility class to generate instances of ProductsPriceLast and ProductsPriceLastResponse.
 */
public class GeneratorProductsPrice {

    /**
     * Method to generate an instance of ProductsPriceLast.
     *
     * @return ProductsPriceLast instance.
     */
    public static ProductsPriceLast generateProductsPrice() {
        return generateProductsPriceLast();
    }

    /**
     * Private method to generate an instance of ProductsPriceLast with hard-coded values.
     *
     * @return ProductsPriceLast instance.
     */
    private static ProductsPriceLast generateProductsPriceLast() {
        return ProductsPriceLast.builder()
                .brandId(1L)
                .productId(35455L)
                .appDate(LocalDateTime.parse("2020-10-31T23:59:59")).build();
    }

    /**
     * Method to generate an instance of ProductsPriceLastResponse with hard-coded values.
     *
     * @return ProductsPriceLastResponse instance.
     */
    public static ProductsPriceLastResponse generateProductsPriceLastResponse(){
        return ProductsPriceLastResponse.builder()
                .brandId(1L)
                .productId(35455L)
                .rateToApply(1L)
                .priceApplicationDates(LocalDateTime.parse("2020-10-31T23:59:59"))
                .finalPrice(35.5)
                .build();
    }
}

