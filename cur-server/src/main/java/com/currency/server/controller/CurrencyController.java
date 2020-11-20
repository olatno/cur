package com.currency.server.controller;

import com.currency.server.service.CurrencyService;
import com.currency.api.CurrencyApi;
import com.currency.api.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST COUNTRIES webservice
 *
 * @author ola
 * @since 09/19/2019.
 */
@RestController
@RequestMapping(value = "/")
public class CurrencyController implements CurrencyApi {

    @Autowired
    private CurrencyService currencyService;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(path = "/borderInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Country> getCountryInfo(@RequestParam String currency){
        return currencyService.getCountryInfo(currency);
    }
}
