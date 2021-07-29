package com.boa.api.response;

import java.util.Map;

public class InfoSignaletiqueResponse extends GenericResponse{
    
    private Data data;

    public InfoSignaletiqueResponse() {
    }
    


    public InfoSignaletiqueResponse(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public InfoSignaletiqueResponse data(Data data) {
        setData(data);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " data='" + getData() + "'" +
            "}";
    }
}