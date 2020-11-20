package com.currency.api.exception;

import org.springframework.http.HttpStatus;

/**
 * CurrencyServiceException class
 *
 * @author ola
 * @since 21/08/202.
 */
public class CurrencyServiceException extends RuntimeException {
    private HttpStatus code;

    public CurrencyServiceException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public CurrencyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Get code
     *
     * @return the code
     */
    public HttpStatus getCode() {
        return this.code;
    }
}