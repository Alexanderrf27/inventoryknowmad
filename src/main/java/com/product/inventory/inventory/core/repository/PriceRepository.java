package com.product.inventory.inventory.core.repository;



import com.product.inventory.inventory.core.domain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The {@code PriceRepository} interface extends the {@code JpaRepository} interface provided by Spring Data JPA.
 * This interface declares a method for querying prices based on a brand ID, product ID, and application date.
 * As a Spring {@code @Repository}, this interface is used to manage {@code Price} entities in a way that is decoupled from the persistence technology or database management system.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * Fetches a list of {@code Price} objects where the brand ID and product ID match the given parameters, and the application date falls within the price's start and end dates.
     * The results are ordered by ascending priority.
     *
     * @param brandId the brand ID for which prices should be fetched.
     * @param productId the product ID for which prices should be fetched.
     * @param appDate the application date that must fall within the price's start and end dates.
     * @return a list of {@code Price} objects matching the query, or an empty list if no records are found.
     */
    @Query("SELECT p FROM Price p JOIN p.brand b " +
            "WHERE b.id = ?1 AND p.productId = ?2 " +
            "AND (?3 BETWEEN p.startDate AND p.endDate) ORDER BY p.priority ASC")
    List<Price> findByProductAndDate(Long brandId, Long productId, LocalDateTime appDate);
}
