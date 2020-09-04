package com.boa.api.service;

import com.boa.api.domain.Tracking;
import com.boa.api.repository.TrackingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
// import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Tracking}.
 */
@Service
@Transactional
public class TrackingService {

    private final Logger log = LoggerFactory.getLogger(TrackingService.class);

    private final TrackingRepository trackingRepository;

    public TrackingService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    /**
     * Save a tracking.
     *
     * @param tracking the entity to save.
     * @return the persisted entity.
     */
    public Tracking save(Tracking tracking) {
        log.debug("Request to save Tracking : {}", tracking);
        return trackingRepository.save(tracking);
    }

    /**
     * Get all the trackings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Tracking> findAll(Pageable pageable) {
        log.debug("Request to get all Trackings");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), 
        Sort.by(Sort.Direction.DESC, "dateRequest")); 
        return trackingRepository.findAll(pageable);
    }

    /*@Transactional(readOnly = true)
    public Page<Tracking> findByCriteria(Instant dateDeb, Instant dateFin, String requestId, 
    Pageable pageable) {
        log.info("Request to get Transactions By Criteria####");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), 
        Sort.by(Sort.Direction.DESC, "dateRequest")); 
        return trackingRepository.findByCriteria(dateDeb, dateFin, requestId, pageable);
    }*/

    /**
     * Get one tracking by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Tracking> findOne(Long id) {
        log.debug("Request to get Tracking : {}", id);
        return trackingRepository.findById(id);
    }

    /**
     * Delete the tracking by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Tracking : {}", id);
        trackingRepository.deleteById(id);
    }

    public Tracking createTracking(String code, String endPoint, String result, String token, 
    String login, String requestId, String requestTr){
        Tracking tracking = new Tracking();
        tracking.setCodeResponse(code);
        tracking.setDateResponse(Instant.now());
        tracking.setEndPoint(endPoint);
        tracking.setLoginActeur(login);
        tracking.setResponseTr(result);
        //tracking.setDateRequest(Instant.now());
        tracking.setRequestId(requestId);
        tracking.setRequestTr(requestTr);
        return tracking;
    }
}
