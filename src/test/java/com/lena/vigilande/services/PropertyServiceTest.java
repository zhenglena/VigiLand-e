package com.lena.vigilande.services;

import com.lena.vigilande.dao.PropertyDao;
import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.dtos.ScofflawsResponse;
import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.helpers.TestHelpers;
import com.lena.vigilande.mappers.ViolationsMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class PropertyServiceTest {
    @Mock
    private PropertyDao dao;
    @InjectMocks
    private PropertyService service;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }
    @Test
    public void getViolationsResponseByAddress_successful_returnsViolationsResponse() {
        ViolationsResponse expectedResponse = new ViolationsResponse(
                LocalDate.of(2025, Month.AUGUST, 15),
                4,
                TestHelpers.EXPECTED_VIOLATION_LIST,
                false
        );
        String address = TestHelpers.VIOLATION_ADDRESS;
        when(dao.findViolationsByAddress(address)).thenReturn(TestHelpers.EXPECTED_VIOLATION_LIST);
        when(dao.findScofflawByAddress(address)).thenReturn(null);

        Optional<ViolationsResponse> actualResponse = service.getViolationsResponseByAddress(address);

        assertTrue(actualResponse.isPresent());
        assertEquals(expectedResponse, actualResponse.get());
    }

    @Test
    public void getViolationsResponseByAddress_noAddressFound_returnsEmptyViolationsResponse() {
        String address =  "123 W GRACE ST";
        when(dao.findViolationsByAddress(address)).thenReturn(new ArrayList<>());
        when(dao.findScofflawByAddress(address)).thenReturn(null);

        Optional<ViolationsResponse> actualResponse = service.getViolationsResponseByAddress(address);

        assertTrue(actualResponse.isEmpty());
    }

    @Test
    public void getScofflawsByDate_successful_returnsList() {
        when(dao.findScofflawsByDate(TestHelpers.SCOFFLAW_DATE)).thenReturn(TestHelpers.EXPECTED_SCOFFLAW_LIST);

        Optional<ScofflawsResponse> actualResponse= service.getScofflawsByDate(TestHelpers.SCOFFLAW_DATE);

        assertTrue(actualResponse.isPresent());
    }

    @Test
    public void getScofflawsByDate_noScofflawsFound_returnsEmpty() {
        when(dao.findScofflawsByDate(TestHelpers.SCOFFLAW_DATE)).thenReturn(new ArrayList<>());

        Optional<ScofflawsResponse> actualResponse= service.getScofflawsByDate(TestHelpers.SCOFFLAW_DATE);

        assertTrue(actualResponse.isEmpty());
    }
}
