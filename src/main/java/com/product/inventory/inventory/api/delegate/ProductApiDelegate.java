package com.product.inventory.inventory.api.delegate;


import com.product.inventory.inventory.api.ProductManagerApiDelegate;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;
import com.product.inventory.inventory.core.ports.DefaultProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.CompletableFuture;

/**
 * ProductApiDelegate
 */
@RestControllerAdvice
public class ProductApiDelegate implements ProductManagerApiDelegate {

    private final DefaultProductPriceService defaultProductPriceService;

    @Autowired
    public ProductApiDelegate(DefaultProductPriceService defaultProductPriceService) {
        this.defaultProductPriceService = defaultProductPriceService;
    }

    /**
     * @param productsPriceLast
     * @return
     */
    public CompletableFuture<ResponseEntity<ProductsPriceLastResponse>> getPriceLast(@RequestBody ProductsPriceLast productsPriceLast) {
        return CompletableFuture.completedFuture(defaultProductPriceService.getProductPriceLast(productsPriceLast)
                .map(lastResponse -> ResponseEntity.ok().body(lastResponse))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }
}
