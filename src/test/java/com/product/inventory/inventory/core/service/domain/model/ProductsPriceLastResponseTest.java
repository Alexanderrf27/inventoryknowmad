package com.product.inventory.inventory.core.service.domain.model;

import com.product.inventory.inventory.core.domain.model.ProductsPriceLastResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSettersExcluding;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ProductsPriceLastResponseTest is a test class for ProductsPriceLastResponse.
 * It uses the ExtendWith annotation to use SpringExtension which integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model.
 */
@ExtendWith(SpringExtension.class)
class ProductsPriceLastResponseTest {

    /**
     * Test method for ProductsPriceLastResponse class.
     * It tests the validation of the ProductsPriceLastResponse's bean constructor, hashCode and getter and setters methods.
     * Excluding "priceApplicationDates" from hashCode and getters and setters validation.
     */
    @Test
    @DisplayName("Test Context ProductsPriceLastResponse")
    void test() {
        assertThat(ProductsPriceLastResponse.class,
                allOf(hasValidBeanConstructor(),
                        hasValidBeanHashCodeExcluding("priceApplicationDates"),
                        hasValidGettersAndSettersExcluding("priceApplicationDates"))
        );
    }
}
