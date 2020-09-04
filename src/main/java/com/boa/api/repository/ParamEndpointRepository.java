package com.boa.api.repository;

import com.boa.api.domain.ParamEndpoint;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ParamEndpoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamEndpointRepository extends JpaRepository<ParamEndpoint, Long> {

    ParamEndpoint findByCodeParam(String codeparam);

}
