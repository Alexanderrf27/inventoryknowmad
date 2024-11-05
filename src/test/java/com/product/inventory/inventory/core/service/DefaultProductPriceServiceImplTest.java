package com.product.inventory.inventory.core.service;


import com.product.inventory.inventory.core.domain.Brand;
import com.product.inventory.inventory.core.domain.entity.Price;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;
import com.product.inventory.inventory.core.exeptions.WebRuntimeException;
import com.product.inventory.inventory.core.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.product.inventory.inventory.core.util.GeneratorProductsPrice.generateProductsPrice;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * DefaultProductPriceServiceImplTest is a test class for the DefaultProductPriceServiceImpl class.
 * It uses the ExtendWith annotation to use SpringExtension which integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model.
 */
@ExtendWith(SpringExtension.class)
class DefaultProductPriceServiceImplTest {

    /**
     * Mock of PriceRepository.
     */
    @Mock
    private PriceRepository priceRepository;

    /**
     * Instance of the class to test, DefaultProductPriceServiceImpl.
     */
    private DefaultProductPriceServiceImpl productService;

    /**
     * Setup method, executed before each test.
     * It initializes the mock and the class instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new DefaultProductPriceServiceImpl(priceRepository);
    }

    /**
     * Test method.
     * It tests that the getProductPriceLast method of the service returns the expected result when a Price is found.
     */
    @Test
    @DisplayName("Test Context testGet Product Price Last Returns Price Response")
    void testGetProductPriceLast_ReturnsPriceResponse() {

        ProductsPriceLast productsPriceLast =  ProductsPriceLast.builder()
                .brandId(1L)
                .productId(35455L)
                .appDate(LocalDateTime.parse("2020-06-14T10:00:00")).build();

        Price price = new Price();
        price.setPrice(9.99);
        price.setProductId(35455L);
        price.setEndDate(LocalDateTime.parse("2020-06-14T10:00:00"));
        price.setBrand(new Brand(1L, "ZARA"));
        price.setPriceList(33L);
        price.setPriority(1);

        List<Price> prices = new ArrayList<>();
        prices.add(price);

        when(priceRepository.findByProductAndDate(
                productsPriceLast.getBrandId(),
                productsPriceLast.getProductId(),
                productsPriceLast.getAppDate())).thenReturn(prices);

                Optional<ProductsPriceLastResponse> result = productService.getProductPriceLast(productsPriceLast);

        assertTrue(result.isPresent(), "El resultado debe estar presente");
        ProductsPriceLastResponse response = result.get();
        assertEquals(price.getPrice(), response.getFinalPrice(), "El precio final debe coincidir");
        assertEquals(price.getProductId(), response.getProductId(), "El ID del producto debe coincidir");
        assertEquals(price.getEndDate(), response.getPriceApplicationDates(), "La fecha de aplicación debe coincidir");
        assertEquals(price.getBrand().getId(), response.getBrandId(), "El ID de la marca debe coincidir");
        assertEquals(price.getPriceList(), response.getRateToApply(), "El rate a aplicar debe coincidir");

        verify(priceRepository, times(1)).findByProductAndDate(
                productsPriceLast.getBrandId(),
                productsPriceLast.getProductId(),
                productsPriceLast.getAppDate());
    }

    /**
     * Test method.
     * It tests that the getProductPriceLast method of the service returns an empty Optional when no Price is found.
     */
    @Test
    @DisplayName("Test Context testGet Product PriceLast Returns Empty Optional When No Price Found")
    void testGetProductPriceLast_ReturnsEmptyOptional_WhenNoPriceFound() {
        ProductsPriceLast productsPriceLast = generateProductsPrice();

        when(priceRepository.findByProductAndDate(productsPriceLast.getBrandId(),
                productsPriceLast.getProductId(),
                productsPriceLast.getAppDate())).thenReturn(new ArrayList<>());


        WebRuntimeException exception = assertThrows(WebRuntimeException.class, () -> productService.getProductPriceLast(productsPriceLast).orElseThrow());
        assertEquals("No final price associated with the product has been found", exception.getMessage());
        verify(priceRepository, times(1)).findByProductAndDate(productsPriceLast.getBrandId(),
                productsPriceLast.getProductId(),
                productsPriceLast.getAppDate());
    }

    /**
     * Test method.
     * It tests that the getProductPriceLast method of the service throws an exception when no final price is found.
     */
    @Test
    @DisplayName("Test Context Product Price Last Throws Exception WhenNo Final Price Found")
    void testGetProductPriceLast_ThrowsException_WhenNoFinalPriceFound() {

        ProductsPriceLast productsPriceLast = new ProductsPriceLast();
        productsPriceLast.setBrandId(1L);
        productsPriceLast.setProductId(1L);
        productsPriceLast.setAppDate(LocalDateTime.parse("2020-10-31T23:59:59"));

        when(priceRepository.findByProductAndDate(productsPriceLast.getBrandId(),
                productsPriceLast.getProductId(),
                productsPriceLast.getAppDate())).thenReturn(new ArrayList<>());


        assertThrows(WebRuntimeException.class, () -> productService.getProductPriceLast(productsPriceLast));
    }

    /**
     * Test method.
     * It tests that the getProductPriceLast method of the service throws an exception when no final price is found.
     */
    @Test
    @DisplayName("Test ProductPriceLast NullProductsPriceLast ThrowsWebRuntimeException")
    void testGetProductPriceLast_NullProductsPriceLast_ThrowsWebRuntimeException() {
        assertThrows(WebRuntimeException.class, () -> productService.getProductPriceLast(null));
    }
}
