package com.currency.server.service.impl;


import com.currency.api.model.Country;
import com.currency.server.client.RestCurrencyClient;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceImplTest {

    @Mock
    private RestCurrencyClient restCurrencyClient;
    @InjectMocks
    CurrencyServiceImpl currencyService;

    @Test
    public void testGetCountryInfo() throws Exception{
        Whitebox.setInternalState(currencyService, "restCurrencyClient", restCurrencyClient);

        Object[] objectsResponse = (Object[]) getObjectFromJson("countriesResponse.json", Object[].class);
        when(restCurrencyClient.getCountries("eur")).thenReturn(objectsResponse);
        Object objectResponse =  getObjectFromJson("countryResponse.json", Object.class);
        when(restCurrencyClient.getCountry("fra")).thenReturn(objectResponse);

        Object[] moviesResponse = (Object[]) getObjectFromJson("movies.json", Object[].class);
        Object[] userResponse =  (Object[])getObjectFromJson("user.json", Object[].class);



        printMovies(userResponse, moviesResponse, 8888);

        List<Country> countries = currencyService.getCountryInfo("eur");
        assertThat(restCurrencyClient.getCountries("eur")).isNotEmpty();
        assertThat(restCurrencyClient.getCountry("fra")).isNotNull();
        assertThat(countries.size()).isGreaterThan(0);
    }
    private Object getObjectFromJson(String filename, Class clazz) throws Exception {
        String jsonString = IOUtils.toString(this.getClass().getClassLoader().getResource(filename), StandardCharsets.UTF_8);
        File file = ResourceUtils.getFile("classpath:"+filename);
        FileReader reader = new FileReader(file);
        Gson gson = new Gson();
        return gson.fromJson(reader, clazz);
    }

    private void printMovies(Object[] users, Object[] movies, int userId){
        List<String> title = new ArrayList<>();
        List<Double> movieIds = new ArrayList<>();
        for(Object user : users){
            Map<String, String> userMap = (Map)user;
            Object idObj = userMap.get("id");
            if(Double.compare(Double.valueOf(userId), (Double) idObj) == 0){
                Object friendObj = userMap.get("friends");
                movieIds = (ArrayList)friendObj;
            }
        }
        for(Double id : movieIds){
            for(Object movie : movies){
                Map<String, String> objMap = (Map)movie;
                Object movieIdObj = objMap.get("id");

                if(Double.compare(id, (Double)movieIdObj) == 0){
                    title.add(objMap.get("title"));
                }
            }
        }
        List<String> printList = title.stream().sorted(
                comparing(n-> n)).limit(3).collect(Collectors.toList());
        System.out.println(printList);
    }
}
