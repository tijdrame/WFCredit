package com.boa.api.web.rest;

import com.boa.api.domain.ParamEndpoint;
import com.boa.api.service.ParamEndpointService;
import com.boa.api.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import springfox.documentation.annotations.ApiIgnore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.boa.api.domain.ParamEndpoint}.
 */
@RestController
@RequestMapping("/api")
@ApiIgnore
public class ParamEndpointResource {

    private final Logger log = LoggerFactory.getLogger(ParamEndpointResource.class);

    private static final String ENTITY_NAME = "paramEndpoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamEndpointService paramEndpointService;

    public ParamEndpointResource(ParamEndpointService paramEndpointService) {
        this.paramEndpointService = paramEndpointService;
    }

    /**
     * {@code POST  /param-endpoints} : Create a new paramEndpoint.
     *
     * @param paramEndpoint the paramEndpoint to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramEndpoint, or with status {@code 400 (Bad Request)} if the paramEndpoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-endpoints")
    public ResponseEntity<ParamEndpoint> createParamEndpoint(@RequestBody ParamEndpoint paramEndpoint) throws URISyntaxException {
        log.debug("REST request to save ParamEndpoint : {}", paramEndpoint);
        if (paramEndpoint.getId() != null) {
            throw new BadRequestAlertException("A new paramEndpoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamEndpoint result = paramEndpointService.save(paramEndpoint);
        return ResponseEntity.created(new URI("/api/param-endpoints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-endpoints} : Updates an existing paramEndpoint.
     *
     * @param paramEndpoint the paramEndpoint to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramEndpoint,
     * or with status {@code 400 (Bad Request)} if the paramEndpoint is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramEndpoint couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-endpoints")
    public ResponseEntity<ParamEndpoint> updateParamEndpoint(@RequestBody ParamEndpoint paramEndpoint) throws URISyntaxException {
        log.debug("REST request to update ParamEndpoint : {}", paramEndpoint);
        if (paramEndpoint.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParamEndpoint result = paramEndpointService.save(paramEndpoint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, paramEndpoint.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /param-endpoints} : get all the paramEndpoints.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramEndpoints in body.
     */
    @GetMapping("/param-endpoints")
    public List<ParamEndpoint> getAllParamEndpoints() {
        log.debug("REST request to get all ParamEndpoints");
        return paramEndpointService.findAll();
    }

    /**
     * {@code GET  /param-endpoints/:id} : get the "id" paramEndpoint.
     *
     * @param id the id of the paramEndpoint to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramEndpoint, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-endpoints/{id}")
    public ResponseEntity<ParamEndpoint> getParamEndpoint(@PathVariable Long id) {
        log.debug("REST request to get ParamEndpoint : {}", id);
        Optional<ParamEndpoint> paramEndpoint = paramEndpointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paramEndpoint);
    }

    /**
     * {@code DELETE  /param-endpoints/:id} : delete the "id" paramEndpoint.
     *
     * @param id the id of the paramEndpoint to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-endpoints/{id}")
    public ResponseEntity<Void> deleteParamEndpoint(@PathVariable Long id) {
        log.debug("REST request to delete ParamEndpoint : {}", id);
        paramEndpointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
