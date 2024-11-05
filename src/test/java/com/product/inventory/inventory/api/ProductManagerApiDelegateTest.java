package com.product.inventory.inventory.api;


import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.product.inventory.inventory.core.util.GeneratorProductsPrice.generateProductsPrice;
import static com.product.inventory.inventory.core.util.GeneratorProductsPrice.generateProductsPriceLastResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * ProductManagerApiDelegateTest is a test class for ProductManagerApiDelegate.
 * It uses the Spring Extension for JUnit5 to provide Spring context for testing.
 * The class under test is mocked using Mockito.
 */
@ExtendWith(SpringExtension.class)
class ProductManagerApiDelegateTest {

    /**
     * Mock instance of ProductManagerApiDelegate used for testing.
     */
    @Mock
    private ProductManagerApiDelegate apiDelegate;

    /**
     * Sets up the tests by initializing mocks.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test method for ProductManagerApiDelegate's getPriceLast method.
     * It tests the scenario where the getPriceLast operation is successful.
     *
     * @throws ExecutionException   if the computation threw an exception
     * @throws InterruptedException if the current thread was interrupted while waiting
     */
    @Test
    @DisplayName("Test Context get Price Fast Test")
    void getPriceLastTest() throws ExecutionException, InterruptedException {
        ProductsPriceLast productsPriceLast = generateProductsPrice();
        ProductsPriceLastResponse response = generateProductsPriceLastResponse();

        ResponseEntity<ProductsPriceLastResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
        CompletableFuture<ResponseEntity<ProductsPriceLastResponse>> completableFuture = CompletableFuture.completedFuture(responseEntity);

        when(apiDelegate.getPriceLast(productsPriceLast)).thenReturn(completableFuture);

        CompletableFuture<ResponseEntity<ProductsPriceLastResponse>> result = apiDelegate.getPriceLast(productsPriceLast);
        ResponseEntity<ProductsPriceLastResponse> actualResult = result.get();

        assertEquals(responseEntity, actualResult);
        verify(apiDelegate, times(1)).getPriceLast(productsPriceLast);
    }
}

