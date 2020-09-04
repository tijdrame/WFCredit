package com.boa.api.repository;

import java.time.Instant;

import com.boa.api.domain.Tracking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tracking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

    /*@Query("Select t FROM Tracking t  WHERE (t.dateRequest BETWEEN ?1 AND ?2) and upper(t.requestId)like ?3")
	Page<Tracking> findByCriteria(
			Instant dateDeb, Instant dateFin, String requestId, Pageable pageable);*/
}
