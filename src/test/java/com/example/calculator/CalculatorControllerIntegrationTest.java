package com.example.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE = "/api/v1/calculator";

    @Test
    @DisplayName("POST /add returns correct result")
    void postAdd() throws Exception {
        mockMvc.perform(post(BASE + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 8, \"b\": 5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(13.0))
                .andExpect(jsonPath("$.expression").value("8 + 5 = 13"));
    }

    @Test
    @DisplayName("GET /divide returns 2.5")
    void getDivide() throws Exception {
        mockMvc.perform(get(BASE + "/divide").param("a", "10").param("b", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2.5));
    }

    @Test
    @DisplayName("POST /divide by zero returns 400")
    void divideByZeroReturns400() throws Exception {
        mockMvc.perform(post(BASE + "/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 5, \"b\": 0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("DIVISION_BY_ZERO"));
    }

    @Test
    @DisplayName("POST /add with missing field returns 400")
    void missingFieldReturns400() throws Exception {
        mockMvc.perform(post(BASE + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 5}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("VALIDATION_ERROR"));
    }

    @Test
    @DisplayName("GET /sqrt of 25 returns 5")
    void getSqrt() throws Exception {
        mockMvc.perform(get(BASE + "/sqrt").param("a", "25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    @DisplayName("GET /sqrt of negative returns 400")
    void sqrtNegativeReturns400() throws Exception {
        mockMvc.perform(get(BASE + "/sqrt").param("a", "-4"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("NEGATIVE_SQRT"));
    }
}
