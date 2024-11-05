package com.product.inventory.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryApplicationTest {

    /**
     * This test ensures that the Spring Application context loads correctly.
     *
     * @param context The Spring Web Application context.
     */
    @Test
    void contextLoads(WebApplicationContext context) {
        assertNotNull(context);
    }
}
