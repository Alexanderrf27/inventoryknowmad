package com.product.inventory.inventory.core.domain.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;


/**
 * The {@code ProductsPriceLast} class represents the request body for a product's price information retrieval API.
 * It contains the necessary identifiers and a timestamp to look up the latest price for a particular product.
 *
 * Validation annotations are used to enforce constraints such as non-null values and specific size limits.
 * The class is equipped with Lombok annotations to generate boilerplate code like getters, setters, constructors, etc.
 */
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsPriceLast {

    /**
     * Identifier of the brand chain group (1 = ZARA).
     * It is mandatory and must be a single digit.
     */
    @Schema(description = "Group chain id (1 = ZARA)")
    @NotNull
    @NotBlank(message = "brand Id is required")
    @Size(min = 1, max = 1)
    private Long brandId;

    /**
     * Identifier of the product for which the price information is being retrieved.
     * It is mandatory and must be a number of 5 to 6 digits.
     */
    @NotNull
    @NotBlank(message = "product Id  is required")
    @Size(min = 5, max = 6)
    @Schema(description = "Represents product identifier")
    private Long productId;

    /**
     * The application date and time at which the price is being retrieved.
     * It is mandatory and must follow the "yyyy-MM-dd HH:mm:ss" format.
     */
    @NotNull
    @NotBlank(message = "application date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Represents application date")
    private LocalDateTime appDate;
}

