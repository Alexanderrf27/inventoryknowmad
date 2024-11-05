package com.product.inventory.inventory.core.service.domain;


import com.product.inventory.inventory.core.domain.Brand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * BrandTest is a test class for Brand.
 * It uses the ExtendWith annotation to use SpringExtension which integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model.
 */
@ExtendWith(SpringExtension.class)
class BrandTest {

    /**
     * Test method for Brand class.
     * It tests the validation of the Brand's getters and setters,
     * the bean constructor, toString and hashCode methods.
     */
    @Test
    @DisplayName("Test Context Brand")
    void test() {
        assertThat(Brand.class,
                allOf(hasValidGettersAndSetters(),
                        hasValidBeanConstructor(),
                        hasValidBeanToString(),
                        hasValidBeanHashCode())
        );
    }
}

