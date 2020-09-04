package com.boa.api.response;

import java.util.Map;

public class RatingResponse extends GenericResponse {
    
    private Map<String, Object> dataRating;


    public RatingResponse() {
    }

    public RatingResponse(Map<String,Object> dataRating) {
        this.dataRating = dataRating;
    }

    public Map<String,Object> getDataRating() {
        return this.dataRating;
    }

    public void setDataRating(Map<String,Object> dataRating) {
        this.dataRating = dataRating;
    }

    public RatingResponse dataRating(Map<String,Object> dataRating) {
        this.dataRating = dataRating;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " dataRating='" + getDataRating() + "'" +
            "}";
    }
    
}
