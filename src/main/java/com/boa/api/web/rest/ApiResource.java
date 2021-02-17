package com.boa.api.web.rest;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.boa.api.request.InfoSignaletiqueRequest;
import com.boa.api.request.RatingRequest;
import com.boa.api.response.InfoSignaletiqueResponse;
import com.boa.api.response.RatingResponse;
import com.boa.api.service.ApiService;
import com.boa.api.service.utils.ICodeDescResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiResource {
    
    private final Logger log = LoggerFactory.getLogger(ApiResource.class);

    private final ApiService apiService;

    public ApiResource(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/rating")
    public ResponseEntity<RatingResponse> authorisation(@RequestBody RatingRequest ratingRequest,
            HttpServletRequest request) {
        log.info("Enter in authorisation [{}]", ratingRequest);
        RatingResponse response = new RatingResponse();
        // doControl
        if(StringUtils.isEmpty(ratingRequest.getClientCode())||
        StringUtils.isEmpty(ratingRequest.getCountry())
        ) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        
        response = apiService.rating(ratingRequest, request);
        log.info("Response in authorisation [{}]", response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    } 

    @PostMapping("/getInfosSignaletiquesClient")
    public ResponseEntity<InfoSignaletiqueResponse> getInfosSignaletiquesClient(@RequestBody InfoSignaletiqueRequest signaletiqueRequest,
            HttpServletRequest request) {
        log.info("Enter in authorisation [{}]", signaletiqueRequest);
        InfoSignaletiqueResponse response = new InfoSignaletiqueResponse();
        if(StringUtils.isEmpty(signaletiqueRequest.getClientCode())||
        StringUtils.isEmpty(signaletiqueRequest.getCountry())
        ) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        
        response = apiService.getInfosSignaletiquesClient(signaletiqueRequest, request);
        log.info("Response in authorisation [{}]", response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    } 
}
