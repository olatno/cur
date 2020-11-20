package com.currency.api;

import com.currency.api.model.Country;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * REST API for Currency.
 *
 * @author ola
 * @since 21-08-2020.
 */
@Api(value = "/")
@SwaggerDefinition(
        info = @Info(
                description = "Currency Service", title = "", version = ""
        )
)
public interface CurrencyApi {

    /**
     * Search the address.
     *
     * @param currencyCode  the three letter currency code
     * @return the suggested address list
     */
    @RequestMapping(value = "/borderInfo", method = RequestMethod.GET)
    @ApiOperation(value = "Get border info.")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Request processed successfully."),
            @ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Request failed due to malformed syntax."),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "The server has encountered an error."),
    })
    List<Country> getCountryInfo(@RequestParam String currencyCode);
}
