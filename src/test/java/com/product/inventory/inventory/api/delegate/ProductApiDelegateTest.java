package com.product.inventory.inventory.api.delegate;


import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;
import com.product.inventory.inventory.core.ports.DefaultProductPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.product.inventory.inventory.core.util.GeneratorProductsPrice.generateProductsPrice;
import static com.product.inventory.inventory.core.util.GeneratorProductsPrice.generateProductsPriceLastResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Unit test class for ProductApiDelegate. These tests ensure that the appropriate responses
 * are returned from the ProductApiDelegate methods when given certain inputs.
 *
 * The SpringExtension class integrates the Spring TestContext Framework into JUnit 5's Jupiter
 * programming model and is used here for extending the test execution environment.
 */
@ExtendWith(SpringExtension.class)
class ProductApiDelegateTest {

    @Mock
    private DefaultProductPriceService productPriceService;

    private ProductApiDelegate apiDelegate;

    /**
     * This method is executed before each test. It opens the Mockito mocks and
     * initializes the ProductApiDelegate.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apiDelegate = new ProductApiDelegate(productPriceService);
    }

    /**
     * This test verifies that a correct OK response is returned when
     * getProductPriceLast method of ProductPriceService returns an optional with value.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @DisplayName("Test Context get  Price Last Returns Ok Response")
    void getPriceLast_ReturnsOkResponse() throws ExecutionException, InterruptedException {
        ProductsPriceLast productsPriceLast = generateProductsPrice();

        ProductsPriceLastResponse response = generateProductsPriceLastResponse();

        ResponseEntity<ProductsPriceLastResponse> responseEntity = ResponseEntity.ok(response);
        when(productPriceService.getProductPriceLast(productsPriceLast)).thenReturn(Optional.of(response));
        CompletableFuture<ResponseEntity<ProductsPriceLastResponse>> result = apiDelegate.getPriceLast(productsPriceLast);
        ResponseEntity<ProductsPriceLastResponse> actualResult = result.get();

        assertEquals(responseEntity, actualResult);

        verify(productPriceService, times(1)).getProductPriceLast(productsPriceLast);
    }

    /**
     * This test verifies that a correct NOT FOUND response is returned when
     * getProductPriceLast method of ProductPriceService returns an empty optional.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    @DisplayName("Test Context get PriceLast Returns NotFound Response")
    void getPriceLast_ReturnsNotFoundResponse() throws ExecutionException, InterruptedException {
        ProductsPriceLast productsPriceLast = generateProductsPrice();

        when(productPriceService.getProductPriceLast(productsPriceLast)).thenReturn(Optional.empty());
        CompletableFuture<ResponseEntity<ProductsPriceLastResponse>> result = apiDelegate.getPriceLast(productsPriceLast);
        ResponseEntity<ProductsPriceLastResponse> actualResult = result.get();

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());

        verify(productPriceService, times(1)).getProductPriceLast(productsPriceLast);
    }
}
