package com.currency.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CountryTest {
    @InjectMocks
    Country country;

    @Test
    public  void testGetCode(){
        country.setCode("ATB");
        assertThat(country.getCode()).isEqualTo("ATB");
    }

    @Test
    public void testGetBorderList(){
        List<String> borderList = new ArrayList<>();
        borderList.add("RUS");
        borderList.add("BES");
        country.setBorderList(borderList);
        assertThat(country.getBorderList().size()).isEqualTo(2);
    }
}
