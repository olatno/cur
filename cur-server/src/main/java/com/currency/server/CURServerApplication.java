package com.currency.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ola
 * @since 22/08/2020.
 */
@EnableSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.currency"})
public class CURServerApplication {


    /**
     * The main method of this application
     *
     * @param args the args
     */
    public static void main(String[] args) {
        SpringApplication.run(CURServerApplication.class, args);
    }

    /**
     * Returns the RestTemplate spring bean
     *
     * @return Returns the configured rest template
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
