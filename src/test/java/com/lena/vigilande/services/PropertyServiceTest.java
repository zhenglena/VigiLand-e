package com.lena.vigilande.services;

import com.lena.vigilande.dao.PropertyDao;
import com.lena.vigilande.dtos.ScofflawsResponse;
import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.helpers.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PropertyServiceTest {
    @Mock
    private PropertyDao dao;
    @InjectMocks
    private PropertyService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getViolationsResponseByAddress_successful_returnsViolationsResponse() {
        String address = TestHelpers.VIOLATION_ADDRESS;
        when(dao.findViolationsByAddress(address)).thenReturn(TestHelpers.EXPECTED_VIOLATION_LIST);
        when(dao.findScofflawByAddress(address)).thenReturn(null);

        Optional<ViolationsResponse> actualResponse = service.getViolationsResponseByAddress(address);

        assertTrue(actualResponse.isPresent());
        assertEquals(TestHelpers.EXPECTED_VIOLATIONS_RESPONSE, actualResponse.get());
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
