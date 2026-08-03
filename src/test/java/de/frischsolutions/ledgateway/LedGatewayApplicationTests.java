package de.frischsolutions.ledgateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LedGatewayApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testStatusEndpoint() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk());
    }

    @Test
    void testValidSignal() throws Exception {
        mockMvc.perform(post("/api/signals/SUCCESS")
                        .header("X-API-Key", "change-me"))
                .andExpect(status().isOk());
    }

    @Test
    void testUnknownSignal() throws Exception {
        mockMvc.perform(post("/api/signals/UNKNOWN")
                        .header("X-API-Key", "change-me"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMissingApiKey() throws Exception {
        mockMvc.perform(post("/api/signals/SUCCESS"))
                .andExpect(status().isUnauthorized());
    }
}