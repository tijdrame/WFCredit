package com.boa.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.boa.api.web.rest.TestUtil;

public class ParamEndpointTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParamEndpoint.class);
        ParamEndpoint paramEndpoint1 = new ParamEndpoint();
        paramEndpoint1.setId(1L);
        ParamEndpoint paramEndpoint2 = new ParamEndpoint();
        paramEndpoint2.setId(paramEndpoint1.getId());
        assertThat(paramEndpoint1).isEqualTo(paramEndpoint2);
        paramEndpoint2.setId(2L);
        assertThat(paramEndpoint1).isNotEqualTo(paramEndpoint2);
        paramEndpoint1.setId(null);
        assertThat(paramEndpoint1).isNotEqualTo(paramEndpoint2);
    }
}
