package com.product.inventory.inventory.core.domain.entity;


import com.product.inventory.inventory.core.domain.Brand;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
/**
 * The {@code Price} class represents a record in the "PRICES" table of an e-commerce database.
 * It encapsulates the data related to the price of a product at a certain period, including the applied rates.
 * This class is a JPA Entity and can be used to map records in the "PRICES" table.
 * It includes Lombok annotations for automatic generation of getters, setters, constructors, and toString method.
 *
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
public class Price {

    /**
     * Unique identifier for the price record.
     */
    @Id
    @Column(name = "PRICE_ID")
    private Long id;

    /**
     * The brand associated with this price record.
     */
    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private Brand brand;

    /**
     * The date and time when the price becomes applicable.
     */
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    /**
     * The date and time when the price ceases to be applicable.
     */
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    /**
     * The identifier for the price list associated with this price record.
     */
    @Column(name = "PRICE_LIST")
    private Long priceList;

    /**
     * The identifier for the product associated with this price record.
     */
    @Column(name = "PRODUCT_ID")
    private Long productId;

    /**
     * The priority of this price record.
     * It might be used in cases where multiple prices could apply.
     */
    @Column(name = "PRIORITY")
    private Integer priority;

    /**
     * The actual price for the product during the validity period.
     */
    @Column(name = "PRICE")
    private Double price;

    /**
     * The currency in which the price is expressed.
     */
    @Column(name = "CURR")
    private String curr;
}

