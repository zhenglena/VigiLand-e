package com.lena.vigilande.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lena.vigilande.dao.PropertyDao;
import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.dtos.ScofflawsResponse;
import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    public ViolationsResponse getViolationsResponseByAddress(String address) {
        List<Violation> violationsResult = propertyDao.findViolationsByAddress(address.trim().toUpperCase());
        Scofflaw scofflaw = propertyDao.findScofflawByAddress(address.trim().toUpperCase());
        return ViolationsMapper.mapToResponse(violationsResult, scofflaw);
    }

    public ScofflawsResponse getScofflawsByDate(LocalDate fromDate) {
        List<Scofflaw> scofflawList = propertyDao.findScofflawsByDate(fromDate);
        return new ScofflawsResponse(scofflawList);
    }

    public boolean createCommentWithAddress(String address, CommentsRequest request) {
        return propertyDao.createCommentWithAddress(address, request);
    }

}
