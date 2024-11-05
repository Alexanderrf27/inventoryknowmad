package com.product.inventory.inventory.core.service;



import com.product.inventory.inventory.core.domain.entity.Price;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;
import com.product.inventory.inventory.core.exeptions.WebRuntimeException;
import com.product.inventory.inventory.core.ports.DefaultProductPriceService;
import com.product.inventory.inventory.core.repository.PriceRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Comparator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The {@code DefaultProductPriceServiceImpl} class implements the {@code DefaultProductPriceService} interface.
 * This class uses a {@code PriceRepository} instance to fetch prices from the database.
 * It is marked with the Spring {@code @Service} annotation to indicate that it's a service component.
 * This class is also annotated with the Spring {@code @Transactional} annotation to automatically start a database transaction on each method start, and commit it on each method exit (or rollback if method failed due to an error).
 * The class uses the Lombok {@code @Slf4j} annotation to generate a logger field.
 */
@Service
@Transactional
@Slf4j
public class DefaultProductPriceServiceImpl implements DefaultProductPriceService {

    private final PriceRepository priceRepository;

    /**
     * Constructs a new {@code DefaultProductPriceServiceImpl} with the given {@code PriceRepository}.
     *
     * @param priceRepository the repository to use for fetching prices.
     */
    public DefaultProductPriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Retrieves the latest price of a specific product. The product is specified by the {@code ProductsPriceLast} object.
     *
     * @param productsPriceLast a {@code ProductsPriceLast} object containing the product details for which the price is to be fetched.
     * @return An {@code Optional} containing a {@code ProductsPriceLastResponse} object if a price record exists for the specified product, or an empty {@code Optional} if no record exists.
     */
    @Override
    public Optional<ProductsPriceLastResponse> getProductPriceLast(ProductsPriceLast productsPriceLast) {
        Optional<ProductsPriceLast> optionalProductsPriceLast = Optional.ofNullable(productsPriceLast);
        if (optionalProductsPriceLast.isEmpty()) {
            log.error("Received a null ProductsPriceLast object");
            throw new WebRuntimeException("The ProductsPriceLast object cannot be null");
        }
        try {
            List<Price> prices = priceRepository
                    .findByProductAndDate(productsPriceLast.getBrandId(),
                            productsPriceLast.getProductId(),
                            productsPriceLast.getAppDate());

            Price maxPriorityPrice = prices.stream()
                    .max(Comparator.comparing(Price::getPriority))
                    .orElseThrow(NoSuchElementException::new);

            return Optional.of(makePriceResponse(maxPriorityPrice));

        } catch (NoSuchElementException e) {
            log.error("No final price associated with the product has been found", e);
            throw new WebRuntimeException("No final price associated with the product has been found",  e);
        }
    }


    /**
     * Converts a {@code Price} object to a {@code ProductsPriceLastResponse} object.
     *
     * @param price a {@code Price} object.
     * @return a {@code ProductsPriceLastResponse} object.
     */
    private ProductsPriceLastResponse makePriceResponse(Price price) {
        return ProductsPriceLastResponse.builder()
                .finalPrice(price.getPrice())
                .productId(price.getProductId())
                .priceApplicationDates(price.getEndDate())
                .brandId(price.getBrand().getId())
                .rateToApply(price.getPriceList()).build();
    }
}

