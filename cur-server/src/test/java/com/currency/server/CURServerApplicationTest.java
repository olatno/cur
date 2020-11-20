package com.currency.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CURServerApplicationTest {

    @InjectMocks
    private CURServerApplication application;

    @Test
    public void testGetRestTemplate() {
        RestTemplate restTemplate = application.getRestTemplate();
        assertThat(restTemplate.getMessageConverters().size()).isGreaterThan(0);
    }

}