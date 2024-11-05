package com.product.inventory.inventory.core.exeptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
/**
 * WebRuntimeExceptionTest is a test class for WebRuntimeException.
 * It uses the Spring Extension for JUnit5 to provide Spring context for testing.
 */
@ExtendWith(SpringExtension.class)
class WebRuntimeExceptionTest {

    /**
     * Test method for WebRuntimeException's constructor with a message parameter.
     * It tests the scenario where the exception is instantiated with a message and asserts the properties of the exception object.
     */
    @Test
    @DisplayName("Test Context test Web RuntimeException With Message And Default Status")
    void testWebRuntimeException_WithMessageAndDefaultStatus() {
        String message = "Test exception message";
        WebRuntimeException exception = new WebRuntimeException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getErrorCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
    }

    /**
     * Test method for WebRuntimeException's constructor with ErrorCode and HttpStatus parameters.
     * It tests the scenario where the exception is instantiated with an ErrorCode and HttpStatus and asserts the properties of the exception object.
     */
    @Test
    @DisplayName("Test Context test Web Runtime Exception With Error Code And Status")
    void testWebRuntimeException_WithErrorCodeAndStatus() {
        ErrorCode errorCode = () -> "NOT_FOUND";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        WebRuntimeException exception = new WebRuntimeException(errorCode, httpStatus);

        assertNull(exception.getMessage());
        assertEquals(errorCode, exception.getErrorCode());
        assertEquals(httpStatus, exception.getHttpStatus());
    }

    /**
     * Test method for WebRuntimeException's constructor with a message, cause, ErrorCode, and HttpStatus parameters.
     * It tests the scenario where the exception is instantiated with a message, cause, ErrorCode and HttpStatus, and asserts the properties of the exception object.
     */
    @Test
    @DisplayName("Test Context test Web Runtime Exception With Message Cause Error Code And Status")
    void testWebRuntimeException_WithMessageCauseErrorCodeAndStatus() {
        String message = "Test exception message";
        Throwable cause = new RuntimeException();
        ErrorCode errorCode = () -> "INTERNAL_SERVER_ERROR";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        WebRuntimeException exception = new WebRuntimeException(message, cause, errorCode, httpStatus);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(errorCode, exception.getErrorCode());
        assertEquals(httpStatus, exception.getHttpStatus());
    }
}

