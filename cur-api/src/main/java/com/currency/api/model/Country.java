package com.currency.api.model;

import java.util.List;

/**
 * POJO object for Country.
 *
 * @author ola
 * @since 21-08-2020.
 */
public class Country {

    private List<String> borderList;
    private String code;

    public List<String> getBorderList() {
        return borderList;
    }

    public void setBorderList(List<String> borderList) {
        this.borderList = borderList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
