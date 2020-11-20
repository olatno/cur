package com.currency.server.service.impl;

import com.currency.api.model.Country;
import com.currency.server.client.RestCurrencyClient;
import com.currency.server.service.CurrencyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Api service implementation
 *
 * @author ola
 * @since 22/08/2020.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Autowired
    private RestCurrencyClient restCurrencyClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getCountryInfo(String currencyCode) {
        List<Country> countries = new ArrayList<>();
        Assert.hasText(currencyCode, "countryCode is null or empty.");
        Object[] objects = restCurrencyClient.getCountries(currencyCode);
        for(Object object : objects){
            Country country = new Country();
            Map<String, String> objMap = (Map)object;
            country.setCode(objMap.get("alpha3Code"));
            Object arrayOb = objMap.get("borders");
            List<String> borderList = (ArrayList)arrayOb;
            Object currencyObj = objMap.get("currencies");
            List<String> borderCountryList = new ArrayList<>();
            if(borderList.size() > 0) {
                String currencyStr = this.getCurrencyStr(currencyObj);
                borderCountryList = this.getCountries(borderList, currencyStr);
            }
           if(borderCountryList.size() > 0) {
               country.setBorderList(borderCountryList);
           }else{
               country.setBorderList(new ArrayList<>());
           }
            countries.add(country);
        }
        return countries;
    }

    private List<String> getCountries(List<String> borderList, String currency){
        List<String> countries = new ArrayList<>();
        for(String borderStr : borderList){
            Object object = restCurrencyClient.getCountry(borderStr.toLowerCase());
            Map<String, String> objMap = (Map)object;
            Object borderCurrencyObj = null;
            if(objMap != null) {
                borderCurrencyObj = objMap.get("currencies");
            }
            String borderCurrency;
            if(borderCurrencyObj != null) {
                borderCurrency = this.getCurrencyStr(borderCurrencyObj);
                if(!StringUtils.equals(borderCurrency, currency)){
                    countries.add(objMap.get("alpha3Code"));
                }
            }
        }
        return countries;
    }

    private String getCurrencyStr(Object obj){
        String currencyCode = StringUtils.EMPTY;
        List<Map> currencyMap = (ArrayList)obj;
        Map<String, String> linkedHashMap = null;
        if(currencyMap.size() > 0) {
            linkedHashMap = currencyMap.get(0);
        }
        if(linkedHashMap != null){
            currencyCode = linkedHashMap.get("code");
        }else {
            LOG.warn("The currency code is empty");
        }
        return currencyCode;
    }
}
