package com.product.inventory.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@code InventoryApplication} class is the entry point of the Inventory Application.
 * It is annotated with {@code @SpringBootApplication} to denote this as a primary Spring Boot application class.
 * Spring Boot's auto-configuration mechanism, component scanning, and optional configuration will bootstrap this class.
 */
@SpringBootApplication
public class InventoryApplication {

    /**
     * The main method serves as the application's entry point. It launches the Spring Boot application.
     * The {@code SpringApplication.run} method launches the application.
     *
     * @param args an array of command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

}

