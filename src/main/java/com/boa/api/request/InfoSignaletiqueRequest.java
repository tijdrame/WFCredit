package com.boa.api.request;

public class InfoSignaletiqueRequest {
    private String clientCode;
    private String country;


    public InfoSignaletiqueRequest() {
    }

    public InfoSignaletiqueRequest(String clientCode, String country) {
        this.clientCode = clientCode;
        this.country = country;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public InfoSignaletiqueRequest clientCode(String clientCode) {
        setClientCode(clientCode);
        return this;
    }

    public InfoSignaletiqueRequest country(String country) {
        setCountry(country);
        return this;
    }
    
    @Override
    public String toString() {
        return "{" +
            " clientCode='" + getClientCode() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }
    
}
