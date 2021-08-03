package com.boa.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.time.Instant;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.boa.api.domain.ParamEndpoint;
import com.boa.api.domain.Tracking;
import com.boa.api.request.InfoSignaletiqueRequest;
import com.boa.api.request.RatingRequest;
import com.boa.api.response.Account;
import com.boa.api.response.Data;
import com.boa.api.response.InfoSignaletiqueResponse;
import com.boa.api.response.RatingResponse;
import com.boa.api.service.utils.ICodeDescResponse;
import com.boa.api.service.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiService {

    private final Logger log = LoggerFactory.getLogger(ApiService.class);

    private final TrackingService trackingService;
    private final UserService userService;
    private final ParamEndpointService endPointService;
    private final Utils utils;

    public ApiService(TrackingService trackingService, UserService userService, Utils utils,
            ParamEndpointService endPointService) {
        this.trackingService = trackingService;
        this.userService = userService;
        this.utils = utils;
        this.endPointService = endPointService;
    }

    public RatingResponse rating(RatingRequest ratingRequest, HttpServletRequest request) {
        log.info("Enter in ratingResponse [{}]", ratingRequest);
        RatingResponse genericResp = new RatingResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());

        ParamEndpoint endPoint = endPointService.findByCodeParam("rating");
        if (endPoint == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "rating", genericResp.toString(),
                    ratingRequest.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            String jsonStr = new JSONObject().put("clientId", ratingRequest.getClientCode())
                    .put("country", ratingRequest.getCountry()).toString();
            log.info("request confirmation [{}]", jsonStr);
            HttpURLConnection conn = utils.doConnexion(endPoint.getEndPoints(), jsonStr, "application/json", "");
            BufferedReader br = null;
            JSONObject obj = new JSONObject();
            String result = "";
            log.info("resp code envoi [{}]", conn.getResponseCode());

            if (conn != null && conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                obj = new JSONObject(result);
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(result, Map.class);
                obj = new JSONObject(result);
                genericResp.setDataRating(map);
                if (obj.toString().contains("scode") && obj.getJSONObject("data").getInt("scode") == 200) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                    genericResp.setDateResponse(Instant.now());
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), ratingRequest.toString(), genericResp.getResponseReference());
                } else if (obj.toString().contains("scode") && obj.getJSONObject("data").getInt("scode") == 301) {
                    genericResp.setCode(ICodeDescResponse.CLIENT_ABSENT_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.CLIENT_NON_TROUVE);
                    tracking = createTracking(tracking, ICodeDescResponse.CLIENT_ABSENT_CODE, request.getRequestURI(), result,
                            ratingRequest.toString(), genericResp.getResponseReference());
                } else {
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                            genericResp.toString(), ratingRequest.toString(), genericResp.getResponseReference());
                }
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi error ===== [{}]", result);
                obj = new JSONObject(result);
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(result, Map.class);
                obj = new JSONObject(result);
                genericResp.setDataRating(map);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), ratingRequest.toString(), genericResp.getResponseReference());
            }

        } catch (Exception e) {
            log.error("Exception in byRefRequest [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION + " " + e.getMessage());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
                    ratingRequest.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
        return genericResp;
    }

    public InfoSignaletiqueResponse getInfosSignaletiquesClient(InfoSignaletiqueRequest signaletiqueRequest,
			HttpServletRequest request) {
                log.info("Enter in getInfosSignaletiquesClient [{}]", signaletiqueRequest);
                InfoSignaletiqueResponse genericResp = new InfoSignaletiqueResponse();
                Tracking tracking = new Tracking();
                tracking.setDateRequest(Instant.now());
        
                ParamEndpoint endPoint = endPointService.findByCodeParam("signaletique");
                if (endPoint == null) {
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
                    genericResp.setDateResponse(Instant.now());
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "signaletique", genericResp.toString(),
                    signaletiqueRequest.toString(), genericResp.getResponseReference());
                    trackingService.save(tracking);
                    return genericResp;
                }
                try {
                    String jsonStr = new JSONObject().put("client", signaletiqueRequest.getClientCode())
                            .put("pays", signaletiqueRequest.getCountry()).toString();
                    log.info("request signaletique [{}]", jsonStr);
                    HttpURLConnection conn = utils.doConnexion(endPoint.getEndPoints(), jsonStr, "application/json", "");
                    BufferedReader br = null;
                    JSONObject obj = new JSONObject();
                    String result = "";
                    log.info("resp code signaletique [{}]", conn.getResponseCode());
        
                    if (conn != null && conn.getResponseCode() == 200) {
                        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String ligne = br.readLine();
                        while (ligne != null) {
                            result += ligne;
                            ligne = br.readLine();
                        }
                        log.info("resp signaletique ===== [{}]", result);
                        obj = new JSONObject(result);
                        //ObjectMapper mapper = new ObjectMapper();
                        //Map<String, Object> map = mapper.readValue(result, Map.class);
                        obj = new JSONObject(result);
                        //genericResp.setDataSignaletique(map);
                        Data  data = new Data();
                        if (obj.toString().contains("RCOD") && !obj.getJSONObject("data").isNull("RCOD") && 
                        (obj.getJSONObject("data").getString("RCOD").equals("0412"))
                        ) {
                            genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                            genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                            genericResp.setDateResponse(Instant.now());
                            data
                            .adresse(obj.getJSONObject("data").getString("ADRESSE"))
                            .civilite(obj.getJSONObject("data").getString("CIVILITE"))
                            .client(obj.getJSONObject("data").getString("CLIENT"))
                            .dateNaissance(obj.getJSONObject("data").getString("DATE_NAISSANCE"))
                            .identite(obj.getJSONObject("data").getString("IDENTITE"))
                            .naturePersonne(obj.getJSONObject("data").getString("NATURE_PERSONNE"))
                            .nom(obj.getJSONObject("data").getString("NOM"))
                            .prenom(obj.getJSONObject("data").getString("PRENOM"))
                            .profession(obj.getJSONObject("data").getString("PROFESSION"))
                            .rcod(obj.getJSONObject("data").getString("RCOD"))
                            .rmsg(obj.getJSONObject("data").getString("RMSG"))
                            .secteurActivite(obj.getJSONObject("data").getString("SECTEUR_ACTIVITE"))
                            .situationFamiliale(obj.getJSONObject("data").getString("SITUATION_FAMILIALE"))
                            .telephone(obj.getJSONObject("data").getString("TELEPHONE"))
                            .typeClient(obj.getJSONObject("data").getString("TYPE_CLIENT"))
                            .ville(obj.getJSONObject("data").getString("VILLE"))
                            .email(obj.getJSONObject("data").getString("PORTEUR_EMAIL"))
                            .telGsm(obj.getJSONObject("data").getString("PORTEUR_TELPORT"))
                            ;
                            Account account = null;
                            if(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").get("account") instanceof JSONArray) {
                                for (int i = 0; i < obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONArray("account").length(); i++) {
                                    account = new Account();
                                account
                                .agence(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONArray("account").getJSONObject(i).getString("AGENCE"))
                                .agenceLib(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONArray("account").getJSONObject(i).getString("AGENCELIB"))
                                .compte(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONArray("account").getJSONObject(i).getString("COMPTE"))
                                .dateOuverture(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONArray("account").getJSONObject(i).getString("DATOUVERTURE"))
                                ;
                                data.getAccounts().add(account);
                                }
                            }else {
                                account = new Account();
                                account
                                .agence(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONObject("account").getString("AGENCE"))
                                .agenceLib(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONObject("account").getString("AGENCELIB"))
                                .compte(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONObject("account").getString("COMPTE"))
                                .dateOuverture(obj.getJSONObject("data").getJSONObject("ACCOUNTS").getJSONObject("accounts_details").getJSONObject("account").getString("DATOUVERTURE"))
                                ;
                                data.getAccounts().add(account);
                            }

                            genericResp.data(data);
                            tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                                    genericResp.toString(), signaletiqueRequest.toString(), genericResp.getResponseReference());
                        } else if (obj.toString().contains("RCOD") && !obj.getJSONObject("data").isNull("RCOD") && 
                        (obj.getJSONObject("data").getString("RCOD").equals("0413"))
                        ) {
                            data
                            .rcod(obj.getJSONObject("data").getString("RCOD"))
                            .rmsg(obj.getJSONObject("data").getString("RMSG"));
                            genericResp.data(data);
                            genericResp.setCode(ICodeDescResponse.CLIENT_ABSENT_CODE);
                            genericResp.setDateResponse(Instant.now());
                            genericResp.setDescription(ICodeDescResponse.CLIENT_NON_TROUVE);
                            tracking = createTracking(tracking, ICodeDescResponse.CLIENT_ABSENT_CODE, request.getRequestURI(), result,
                                    signaletiqueRequest.toString(), genericResp.getResponseReference());
                        } else {
                            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                            genericResp.setDateResponse(Instant.now());
                            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                                    genericResp.toString(), signaletiqueRequest.toString(), genericResp.getResponseReference());
                        }
                    } else {
                        br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        String ligne = br.readLine();
                        while (ligne != null) {
                            result += ligne;
                            ligne = br.readLine();
                        }
                        log.info("resp envoi error ===== [{}]", result);
                        obj = new JSONObject(result);
                        //ObjectMapper mapper = new ObjectMapper();
                        //Map<String, Object> map = mapper.readValue(result, Map.class);
                        obj = new JSONObject(result);
                        //genericResp.setDataSignaletique(map);
                        genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                        genericResp.setDateResponse(Instant.now()); 
                        genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION+result);
                        tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                                genericResp.toString(), signaletiqueRequest.toString(), genericResp.getResponseReference());
                    }
        
                } catch (Exception e) {
                    log.error("Exception in getInfosSignaletiquesClient [{}]", e);
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION + " " + e.getMessage());
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
                            signaletiqueRequest.toString(), genericResp.getResponseReference());
                }
                trackingService.save(tracking);
                return genericResp;
	}

    public Tracking createTracking(Tracking tracking, String code, String endPoint, String result, String req,
            String reqId) {
        // Tracking tracking = new Tracking();
        tracking.setCodeResponse(code);
        tracking.setDateResponse(Instant.now());
        tracking.setEndPoint(endPoint);
        tracking.setLoginActeur(userService.getUserWithAuthorities().get().getLogin());
        tracking.setResponseTr(result);
        tracking.setRequestTr(req);
        tracking.setRequestId(reqId);
        log.info("saving tracking [{}]", tracking);
        return tracking;
    }

	

}
