package com.currency.server.client;

import com.currency.api.exception.CurrencyServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * The REST Based currencyUri Currency client.
 *
 * @author rp1025804
 * @since 09/20/2019.
 */
@Component
public class RestCurrencyClient {
    private static final Logger LOG = LoggerFactory.getLogger(RestCurrencyClient.class);

    @Autowired
    private RestTemplate restTemplate;
    @Value("${config.rest.country.currency.url}")
    private String currencyUri;
    @Value("${config.rest.country.country.url}")
    private String countryUri;

    /**
     * List of country given currency uri end point.
     *
     * @param currencyCode   The 3 chars currency code
     * @return The {@link Object[]} country and the borders country if any
     */
    public Object[] getCountries(String currencyCode) {

        String uri = this.getUriString(currencyUri, currencyCode);

        ResponseEntity<Object[]> responseEntity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        try {
            responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Object[].class);
        } catch (HttpClientErrorException hcee) {
            LOG.error("Error occurred in the client side.", hcee);
            throw new CurrencyServiceException(hcee.getStatusCode(), hcee.getStatusText());
        } catch (HttpServerErrorException hsee) {
            LOG.error("Error occurred in REST COUNTRIES server side.", hsee);
            throw new CurrencyServiceException(hsee.getStatusCode(), hsee.getStatusText());
        }
        return responseEntity.getBody();
    }

    /**
     * Get country given country uri end point
     *
     * @param countryCode   The 3 chars country code
     * @return The {@link Object[]} containing the borders if any
     */
    public Object getCountry(String countryCode){
        String uri = this.getUriString(countryUri, countryCode);

        ResponseEntity<Object> responseEntity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        try {
            responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);
        } catch (HttpClientErrorException hcee) {
            LOG.error("Error occurred in the client side.", hcee);
            throw new CurrencyServiceException(hcee.getStatusCode(), hcee.getStatusText());
        } catch (HttpServerErrorException hsee) {
            LOG.error("Error occurred in REST COUNTRIES server side.", hsee);
            throw new CurrencyServiceException(hsee.getStatusCode(), hsee.getStatusText());
        }
        return responseEntity.getBody();
    }

    private String getUriString(String endpoint, String path){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);
        builder.path(path);
        String uri ;
        try {
            uri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error occurred while decoding the uri.", e);
            throw new CurrencyServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while decoding the uri.");
        }
        return uri;
    }

    public String getCurrencyUri() {
        return currencyUri;
    }

    public void setCurrencyUri(String currencyUri) {
        this.currencyUri = currencyUri;
    }

    public String getCountryUri() {
        return countryUri;
    }

    public void setCountryUri(String countryUri) {
        this.countryUri = countryUri;
    }
}
