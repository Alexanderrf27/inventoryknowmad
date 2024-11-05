package com.product.inventory.inventory.api;


import com.product.inventory.inventory.api.config.ApiLayerConfig;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


/**
 * ProductManagerApiDelegate is an interface that exposes the end-points related to product management in an e-commerce platform.
 * All the methods defined in this interface are accessible via HTTP methods at the paths defined by the {@code @RequestMapping} and {@code @PostMapping} annotations.
 * The base URL is defined by the ApiLayerConfig.BASE_PATH.
 */
@RestController
@RequestMapping(ApiLayerConfig.BASE_PATH)
public interface ProductManagerApiDelegate {

    /**
     * This method allows you to get the last price of a certain product.
     * The price is determined by the PRICES table in the e-commerce database, which reflects the final price (pvp) and the rate that applies to a product in a chain between certain dates.
     * The HTTP status returned on successful execution of this method is HttpStatus.OK.
     *
     * @param productsPriceLast The product's details for which the last price needs to be fetched.
     * This is passed in the body of the HTTP request.
     *
     * @return A CompletableFuture that will be completed with a ResponseEntity. The ResponseEntity encapsulates the HTTP response,
     * which includes the HTTP status code and the body, which in this case will be an object of type ProductsPriceLastResponse.
     * The ProductsPriceLastResponse object will include the latest price details for the provided product.
     */
    @Operation(description = "In the company's e-commerce database we have the PRICES table that reflects the final price (pvp) and the rate that applies to a product in a chain between certain dates")
    @PostMapping(ApiLayerConfig.POST)
    @ResponseStatus(HttpStatus.OK)
    CompletableFuture<ResponseEntity<ProductsPriceLastResponse>> getPriceLast(@RequestBody ProductsPriceLast productsPriceLast);

}
