package com.currency.api.exception;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.fest.assertions.Assertions.assertThat;

public class CURServiceExceptionTest {

    @Test
    public void testGetAndSetMethods() {

        CurrencyServiceException serviceException = new CurrencyServiceException(HttpStatus.BAD_REQUEST, "message");
        assertThat(serviceException.getCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(serviceException.getMessage()).isEqualTo("message");
    }
}
