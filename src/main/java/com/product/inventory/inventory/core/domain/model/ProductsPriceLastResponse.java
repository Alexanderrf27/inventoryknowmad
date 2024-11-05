package com.product.inventory.inventory.core.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * The {@code ProductsPriceLastResponse} class represents the response body for the API that retrieves the latest price for a particular product.
 * It encapsulates the product information along with the applicable rate, price application dates, and the final price.
 *
 * This class is equipped with Lombok annotations for generating boilerplate code such as getters, setters, constructors, and a builder.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ProductsPriceLastResponse {

    /**
     * Identifier of the product for which the price information has been retrieved.
     */
    @Schema(description = "Represents product identifier")
    private Long productId;

    /**
     * Identifier of the brand chain group (1 = ZARA).
     */
    @Schema(description = "Group chain id (1 = ZARA)")
    private Long brandId;

    /**
     * The rate that applies to the product.
     */
    @Schema(description = "rate to apply to the product")
    private Long rateToApply;

    /**
     * The date and time at which the price information applies.
     */
    @Schema(description = "product price application dates")
    private LocalDateTime priceApplicationDates;

    /**
     * The final price for the product.
     */
    @Schema(description = "final Price application")
    private Double finalPrice;

    /**
     * Overrides the {@code toString} method from {@code Object} class to provide a string representation of {@code ProductsPriceLastResponse}.
     * @return a string representation of {@code ProductsPriceLastResponse}.
     */
    @Override
    public String toString() {
        return "PriceLastResponse{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", rateToApply=" + rateToApply +
                ", priceApplicationDates=" + priceApplicationDates +
                ", finalPrice=" + finalPrice +
                '}';
    }
}

