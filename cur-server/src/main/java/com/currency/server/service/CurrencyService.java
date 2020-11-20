package com.currency.server.service;


import com.currency.api.model.Country;

import java.util.List;

/**
 * The Country information service exposed the borderInfo API.
 *
 * @author ola
 * @since 22/08/2020.
 */
public interface CurrencyService {


    /**
     * Get country border info.
     *
     * @param currency the currency code
     * @return the country border info
     */
    List<Country> getCountryInfo(String currency);
}
