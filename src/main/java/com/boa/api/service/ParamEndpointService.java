package com.boa.api.service;

import com.boa.api.domain.ParamEndpoint;
import com.boa.api.repository.ParamEndpointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ParamEndpoint}.
 */
@Service
@Transactional
public class ParamEndpointService {

    private final Logger log = LoggerFactory.getLogger(ParamEndpointService.class);

    private final ParamEndpointRepository paramEndpointRepository;

    public ParamEndpointService(ParamEndpointRepository paramEndpointRepository) {
        this.paramEndpointRepository = paramEndpointRepository;
    }

    /**
     * Save a paramEndpoint.
     *
     * @param paramEndpoint the entity to save.
     * @return the persisted entity.
     */
    public ParamEndpoint save(ParamEndpoint paramEndpoint) {
        log.debug("Request to save ParamEndpoint : {}", paramEndpoint);
        return paramEndpointRepository.save(paramEndpoint);
    }

    /**
     * Get all the paramEndpoints.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ParamEndpoint> findAll() {
        log.debug("Request to get all ParamEndpoints");
        return paramEndpointRepository.findAll();
    }


    /**
     * Get one paramEndpoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ParamEndpoint> findOne(Long id) {
        log.debug("Request to get ParamEndpoint : {}", id);
        return paramEndpointRepository.findById(id);
    }

    /**
     * Delete the paramEndpoint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ParamEndpoint : {}", id);
        paramEndpointRepository.deleteById(id);
    }

	public ParamEndpoint findByCodeParam(String codeparam) {
		log.info("Request to get Param by Code");
        return paramEndpointRepository.findByCodeParam(codeparam);
	}
}
