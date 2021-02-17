package com.boa.api.response;

import java.util.Map;

public class InfoSignaletiqueResponse extends GenericResponse{
    
    private Map<String, Object> dataSignaletique;

    public InfoSignaletiqueResponse() {
    }

    public InfoSignaletiqueResponse(Map<String,Object> dataSignaletique) {
        this.dataSignaletique = dataSignaletique;
    }

    public Map<String,Object> getDataSignaletique() {
        return this.dataSignaletique;
    }

    public void setDataSignaletique(Map<String,Object> dataSignaletique) {
        this.dataSignaletique = dataSignaletique;
    }

    public InfoSignaletiqueResponse dataSignaletique(Map<String,Object> dataSignaletique) {
        setDataSignaletique(dataSignaletique);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " dataSignaletique='" + getDataSignaletique() + "'" +
            "}";
    }
    
}
