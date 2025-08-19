package com.lena.vigilande.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lena.vigilande.dao.PropertyDao;
import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.dtos.ScofflawsResponse;
import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.mappers.ViolationsMapper;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    public Optional<ViolationsResponse> getViolationsResponseByAddress(String address) {
        List<Violation> violationsResult = propertyDao.findViolationsByAddress(address);
        Scofflaw scofflaw = propertyDao.findScofflawByAddress(address);
        return ViolationsMapper.mapToResponse(violationsResult, scofflaw);
    }

    public Optional<ScofflawsResponse> getScofflawsByDate(LocalDate fromDate) {
        List<Scofflaw> scofflawList = propertyDao.findScofflawsByDate(fromDate);
        if (scofflawList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new ScofflawsResponse(scofflawList));
    }

    public boolean createCommentWithAddress(String address, CommentsRequest request) {
        return propertyDao.createCommentWithAddress(address, request) > 1;
    }

}
