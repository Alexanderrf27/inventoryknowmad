package com.product.inventory.inventory.core.ports;


import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;

import java.util.Optional;

/**
 * The {@code DefaultProductPriceService} interface provides a method for fetching the last known price of a product.
 * Implementations of this interface are responsible for defining the specific logic of this operation.
 */
public interface DefaultProductPriceService {

    /**
     * Retrieves the latest price of a specific product. The product is specified by the {@code ProductsPriceLast} object.
     *
     * @param productsPriceLast a {@code ProductsPriceLast} object containing the product details for which the price is to be fetched.
     * @return An {@code Optional} containing a {@code ProductsPriceLastResponse} object if a price record exists for the specified product, or an empty {@code Optional} if no record exists.
     */
    Optional<ProductsPriceLastResponse> getProductPriceLast(ProductsPriceLast productsPriceLast);
}

