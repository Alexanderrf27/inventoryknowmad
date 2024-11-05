package com.product.inventory.inventory.core.service.domain.model;


import com.product.inventory.inventory.core.domain.model.ProductsPriceLast;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSettersExcluding;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ProductsPriceLastTest is a test class for ProductsPriceLast.
 * It uses the ExtendWith annotation to use SpringExtension which integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model.
 */
@ExtendWith(SpringExtension.class)
class ProductsPriceLastTest {

    /**
     * Test method for ProductsPriceLast class.
     * It tests the validation of the ProductsPriceLast's bean constructor, toString and getter and setters methods.
     * Excluding "appDate" from toString and getters and setters validation.
     */
    @Test
    @DisplayName("Test Context ProductsPriceLast")
    void test() {
        assertThat(ProductsPriceLast.class,
                allOf(hasValidBeanConstructor(),
                        hasValidBeanToStringExcluding("appDate"),
                        hasValidGettersAndSettersExcluding("appDate"))
        );
    }
}

