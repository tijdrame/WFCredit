package com.boa.api.request;

public class RatingRequest {
    private String clientCode;
    private String country;

    public RatingRequest() {
    }

    public RatingRequest(String clientCode, String country) {
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

    public RatingRequest clientCode(String clientCode) {
        this.clientCode = clientCode;
        return this;
    }

    public RatingRequest country(String country) {
        this.country = country;
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
