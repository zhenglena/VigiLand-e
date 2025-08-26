package com.lena.vigilande.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.dtos.ScofflawsResponse;
import com.lena.vigilande.helpers.TestHelpers;
import com.lena.vigilande.services.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private PropertyService propertyService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getViolationsResponseByAddress_addressFound_returnsOK() throws Exception {
        String address = "5036+N+SHERIDAN+RD";

        when(propertyService.getViolationsResponseByAddress(address)).thenReturn(Optional.of(TestHelpers.EXPECTED_VIOLATIONS_RESPONSE));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/{address}", address))
                .andExpect(status().isOk());
    }

    @Test
    public void getViolationsResponseByAddress_addressNotFound_returnsNotFound() throws Exception {
        String address = "123+W+GRACE+ST";

        when(propertyService.getViolationsResponseByAddress(address)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/property/{address}", address))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getScofflawsByDate_scofflawsFound_returnsOK() throws Exception {
        LocalDate fromDate = TestHelpers.SCOFFLAW_DATE;

        when(propertyService.getScofflawsByDate(fromDate)).thenReturn(Optional.of(
                new ScofflawsResponse(TestHelpers.EXPECTED_SCOFFLAW_LIST)));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/property/scofflaws/violations")
                .param("since","2025-03-01"))
                .andExpect(status().isOk());
    }

    @Test
    public void getScofflawsByDate_scofflawsNotFound_returnsNotFound() throws Exception {
        LocalDate fromDate = LocalDate.of(2025, Month.AUGUST, 8);

        when(propertyService.getScofflawsByDate(fromDate)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/property/scofflaws/violations")
                        .param("since","2025-08-08"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCommentWithAddress_successful_returnsCreated() throws Exception {
        String address = "5036+N+SHERIDAN+RD";
        CommentsRequest request = new CommentsRequest("I have cockroaches!", "author");

        when(propertyService.createCommentWithAddress(address, request)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/property/{address}/comments", address)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createCommentWithAddress_unsuccessful_returnsBadRequest() throws Exception {
        String address = "5036+N+SHERIDAN+RD";
        CommentsRequest request = new CommentsRequest("", "");
        when(propertyService.createCommentWithAddress(address, request)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/property/{address}/comments", address)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

}
