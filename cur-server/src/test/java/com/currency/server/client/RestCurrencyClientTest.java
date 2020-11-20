package com.currency.server.client;

import com.currency.api.exception.CurrencyServiceException;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RestCurrencyClientTest {
    private static final String countryUri = "https://restcountries.eu/rest/v2/alpha/";
    private static final String currencyUri = "https://restcountries.eu/rest/v2/currency/";
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    RestCurrencyClient restCurrencyClient;

    @Test
    public void testGetCountries() throws Exception{
        Object[] objects = (Object[]) getObjectFromJson("countriesResponse.json", Object[].class);
        restCurrencyClient.setCurrencyUri(currencyUri);
        restCurrencyClient.getCurrencyUri();
        ResponseEntity<Object[]> responseResponseEntity = new ResponseEntity(objects, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Object[].class))).thenReturn(responseResponseEntity);
        Object[] response = restCurrencyClient.getCountries("eur");
        assertThat(response).isEqualTo(objects);
    }

    @Test(expected = CurrencyServiceException.class)
    public void getCountries_ClientException() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(currencyUri);
        builder.path("");
        String uri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        restCurrencyClient.setCurrencyUri(currencyUri);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Object[].class))).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "currency code is missing."));

        try {
            restCurrencyClient.getCountries("eur");
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(CurrencyServiceException.class);
            CurrencyServiceException he = (CurrencyServiceException) e;
            assertThat(he.getCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(he.getMessage()).isEqualTo("currency code is missing.");
            throw e;
        }
    }

    @Test(expected = CurrencyServiceException.class)
    public void getCountries_ServerException() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(currencyUri);
        builder.path("eur");
        String uri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        restCurrencyClient.setCurrencyUri(currencyUri);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Object[].class))).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error."));

        try {
            restCurrencyClient.getCountries("eur");
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(CurrencyServiceException.class);
            CurrencyServiceException he = (CurrencyServiceException) e;
            assertThat(he.getCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
            assertThat(he.getMessage()).isEqualTo("Unexpected error.");
            throw e;
        }
    }

    @Test
    public void testGetCountry() throws Exception{
        Object object = (Object) getObjectFromJson("countryResponse.json", Object.class);
        restCurrencyClient.setCountryUri(countryUri);
        restCurrencyClient.getCountryUri();
        ResponseEntity<Object> responseResponseEntity = new ResponseEntity(object, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Object.class))).thenReturn(responseResponseEntity);
        Object response = restCurrencyClient.getCountry("fra");
        assertThat(response).isEqualTo(object);
    }

    @Test(expected = CurrencyServiceException.class)
    public void getCountry_ClientException() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(countryUri);
        builder.path("");
        String uri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        restCurrencyClient.setCountryUri(countryUri);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Object.class))).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "country code is missing."));

        try {
            restCurrencyClient.getCountry("fra");
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(CurrencyServiceException.class);
            CurrencyServiceException he = (CurrencyServiceException) e;
            assertThat(he.getCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(he.getMessage()).isEqualTo("country code is missing.");
            throw e;
        }
    }

    @Test(expected = CurrencyServiceException.class)
    public void getCountry_ServerException() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(countryUri);
        builder.path("fra");
        String uri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        restCurrencyClient.setCountryUri(countryUri);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(Object.class))).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error."));

        try {
            restCurrencyClient.getCountry("fra");
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(CurrencyServiceException.class);
            CurrencyServiceException he = (CurrencyServiceException) e;
            assertThat(he.getCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
            assertThat(he.getMessage()).isEqualTo("Unexpected error.");
            throw e;
        }
    }

    private Object getObjectFromJson(String filename, Class clazz) throws Exception {
        String jsonString = IOUtils.toString(this.getClass().getClassLoader().getResource(filename), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        return gson.fromJson(jsonString, clazz);
    }
}