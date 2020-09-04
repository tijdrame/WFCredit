package com.boa.api.web.rest;

import com.boa.api.WfCreditApp;
import com.boa.api.domain.ParamEndpoint;
import com.boa.api.repository.ParamEndpointRepository;
import com.boa.api.service.ParamEndpointService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ParamEndpointResource} REST controller.
 */
@SpringBootTest(classes = WfCreditApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParamEndpointResourceIT {

    private static final String DEFAULT_CODE_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_END_POINTS = "AAAAAAAAAA";
    private static final String UPDATED_END_POINTS = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_01 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_01 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_02 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_02 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_03 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_03 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_04 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_04 = "BBBBBBBBBB";

    @Autowired
    private ParamEndpointRepository paramEndpointRepository;

    @Autowired
    private ParamEndpointService paramEndpointService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParamEndpointMockMvc;

    private ParamEndpoint paramEndpoint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamEndpoint createEntity(EntityManager em) {
        ParamEndpoint paramEndpoint = new ParamEndpoint()
            .codeParam(DEFAULT_CODE_PARAM)
            .endPoints(DEFAULT_END_POINTS)
            .attribute01(DEFAULT_ATTRIBUTE_01)
            .attribute02(DEFAULT_ATTRIBUTE_02)
            .attribute03(DEFAULT_ATTRIBUTE_03)
            .attribute04(DEFAULT_ATTRIBUTE_04);
        return paramEndpoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamEndpoint createUpdatedEntity(EntityManager em) {
        ParamEndpoint paramEndpoint = new ParamEndpoint()
            .codeParam(UPDATED_CODE_PARAM)
            .endPoints(UPDATED_END_POINTS)
            .attribute01(UPDATED_ATTRIBUTE_01)
            .attribute02(UPDATED_ATTRIBUTE_02)
            .attribute03(UPDATED_ATTRIBUTE_03)
            .attribute04(UPDATED_ATTRIBUTE_04);
        return paramEndpoint;
    }

    @BeforeEach
    public void initTest() {
        paramEndpoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamEndpoint() throws Exception {
        int databaseSizeBeforeCreate = paramEndpointRepository.findAll().size();
        // Create the ParamEndpoint
        restParamEndpointMockMvc.perform(post("/api/param-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramEndpoint)))
            .andExpect(status().isCreated());

        // Validate the ParamEndpoint in the database
        List<ParamEndpoint> paramEndpointList = paramEndpointRepository.findAll();
        assertThat(paramEndpointList).hasSize(databaseSizeBeforeCreate + 1);
        ParamEndpoint testParamEndpoint = paramEndpointList.get(paramEndpointList.size() - 1);
        assertThat(testParamEndpoint.getCodeParam()).isEqualTo(DEFAULT_CODE_PARAM);
        assertThat(testParamEndpoint.getEndPoints()).isEqualTo(DEFAULT_END_POINTS);
        assertThat(testParamEndpoint.getAttribute01()).isEqualTo(DEFAULT_ATTRIBUTE_01);
        assertThat(testParamEndpoint.getAttribute02()).isEqualTo(DEFAULT_ATTRIBUTE_02);
        assertThat(testParamEndpoint.getAttribute03()).isEqualTo(DEFAULT_ATTRIBUTE_03);
        assertThat(testParamEndpoint.getAttribute04()).isEqualTo(DEFAULT_ATTRIBUTE_04);
    }

    @Test
    @Transactional
    public void createParamEndpointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramEndpointRepository.findAll().size();

        // Create the ParamEndpoint with an existing ID
        paramEndpoint.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamEndpointMockMvc.perform(post("/api/param-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramEndpoint)))
            .andExpect(status().isBadRequest());

        // Validate the ParamEndpoint in the database
        List<ParamEndpoint> paramEndpointList = paramEndpointRepository.findAll();
        assertThat(paramEndpointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllParamEndpoints() throws Exception {
        // Initialize the database
        paramEndpointRepository.saveAndFlush(paramEndpoint);

        // Get all the paramEndpointList
        restParamEndpointMockMvc.perform(get("/api/param-endpoints?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramEndpoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeParam").value(hasItem(DEFAULT_CODE_PARAM)))
            .andExpect(jsonPath("$.[*].endPoints").value(hasItem(DEFAULT_END_POINTS)))
            .andExpect(jsonPath("$.[*].attribute01").value(hasItem(DEFAULT_ATTRIBUTE_01)))
            .andExpect(jsonPath("$.[*].attribute02").value(hasItem(DEFAULT_ATTRIBUTE_02)))
            .andExpect(jsonPath("$.[*].attribute03").value(hasItem(DEFAULT_ATTRIBUTE_03)))
            .andExpect(jsonPath("$.[*].attribute04").value(hasItem(DEFAULT_ATTRIBUTE_04)));
    }
    
    @Test
    @Transactional
    public void getParamEndpoint() throws Exception {
        // Initialize the database
        paramEndpointRepository.saveAndFlush(paramEndpoint);

        // Get the paramEndpoint
        restParamEndpointMockMvc.perform(get("/api/param-endpoints/{id}", paramEndpoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paramEndpoint.getId().intValue()))
            .andExpect(jsonPath("$.codeParam").value(DEFAULT_CODE_PARAM))
            .andExpect(jsonPath("$.endPoints").value(DEFAULT_END_POINTS))
            .andExpect(jsonPath("$.attribute01").value(DEFAULT_ATTRIBUTE_01))
            .andExpect(jsonPath("$.attribute02").value(DEFAULT_ATTRIBUTE_02))
            .andExpect(jsonPath("$.attribute03").value(DEFAULT_ATTRIBUTE_03))
            .andExpect(jsonPath("$.attribute04").value(DEFAULT_ATTRIBUTE_04));
    }
    @Test
    @Transactional
    public void getNonExistingParamEndpoint() throws Exception {
        // Get the paramEndpoint
        restParamEndpointMockMvc.perform(get("/api/param-endpoints/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamEndpoint() throws Exception {
        // Initialize the database
        paramEndpointService.save(paramEndpoint);

        int databaseSizeBeforeUpdate = paramEndpointRepository.findAll().size();

        // Update the paramEndpoint
        ParamEndpoint updatedParamEndpoint = paramEndpointRepository.findById(paramEndpoint.getId()).get();
        // Disconnect from session so that the updates on updatedParamEndpoint are not directly saved in db
        em.detach(updatedParamEndpoint);
        updatedParamEndpoint
            .codeParam(UPDATED_CODE_PARAM)
            .endPoints(UPDATED_END_POINTS)
            .attribute01(UPDATED_ATTRIBUTE_01)
            .attribute02(UPDATED_ATTRIBUTE_02)
            .attribute03(UPDATED_ATTRIBUTE_03)
            .attribute04(UPDATED_ATTRIBUTE_04);

        restParamEndpointMockMvc.perform(put("/api/param-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamEndpoint)))
            .andExpect(status().isOk());

        // Validate the ParamEndpoint in the database
        List<ParamEndpoint> paramEndpointList = paramEndpointRepository.findAll();
        assertThat(paramEndpointList).hasSize(databaseSizeBeforeUpdate);
        ParamEndpoint testParamEndpoint = paramEndpointList.get(paramEndpointList.size() - 1);
        assertThat(testParamEndpoint.getCodeParam()).isEqualTo(UPDATED_CODE_PARAM);
        assertThat(testParamEndpoint.getEndPoints()).isEqualTo(UPDATED_END_POINTS);
        assertThat(testParamEndpoint.getAttribute01()).isEqualTo(UPDATED_ATTRIBUTE_01);
        assertThat(testParamEndpoint.getAttribute02()).isEqualTo(UPDATED_ATTRIBUTE_02);
        assertThat(testParamEndpoint.getAttribute03()).isEqualTo(UPDATED_ATTRIBUTE_03);
        assertThat(testParamEndpoint.getAttribute04()).isEqualTo(UPDATED_ATTRIBUTE_04);
    }

    @Test
    @Transactional
    public void updateNonExistingParamEndpoint() throws Exception {
        int databaseSizeBeforeUpdate = paramEndpointRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamEndpointMockMvc.perform(put("/api/param-endpoints")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paramEndpoint)))
            .andExpect(status().isBadRequest());

        // Validate the ParamEndpoint in the database
        List<ParamEndpoint> paramEndpointList = paramEndpointRepository.findAll();
        assertThat(paramEndpointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamEndpoint() throws Exception {
        // Initialize the database
        paramEndpointService.save(paramEndpoint);

        int databaseSizeBeforeDelete = paramEndpointRepository.findAll().size();

        // Delete the paramEndpoint
        restParamEndpointMockMvc.perform(delete("/api/param-endpoints/{id}", paramEndpoint.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamEndpoint> paramEndpointList = paramEndpointRepository.findAll();
        assertThat(paramEndpointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
