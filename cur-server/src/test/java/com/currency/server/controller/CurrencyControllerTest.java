package com.currency.server.controller;

import com.currency.api.model.Country;
import com.currency.server.service.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CurrencyControllerTest {

    @Mock
    private CurrencyService currencyService;
    @InjectMocks
    private CurrencyController currencyController;

    @Test
    public void testGetCountryInfo(){
        List<Country> countryList = currencyController.getCountryInfo("eur");
        assertThat(countryList.size()).isEqualTo(0);
    }
}
