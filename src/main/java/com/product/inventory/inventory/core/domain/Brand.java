package com.product.inventory.inventory.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The {@code Brand} class represents a record in the "BRANDS" table.
 * This class encapsulates the data related to a brand in the e-commerce system.
 * As a JPA Entity, it is used to map records in the "BRANDS" table.
 * It includes Lombok annotations for automatic generation of getters, setters, constructors, and toString method.
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BRANDS")
public class Brand {

    /**
     * Unique identifier for the brand.
     */
    @Id
    @Column(name = "BRAND_ID")
    private Long id;

    /**
     * The name of the brand.
     */
    @Column(name = "NAME")
    private String name;
}

