package com.lena.vigilande.services;

import com.lena.vigilande.dao.PropertyDao;
import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.dtos.ScofflawsResponse;
import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.mappers.ViolationsMapper;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;
import com.lena.vigilande.util.PathInputParser;
import com.lena.vigilande.util.PathInputChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyDao propertyDao;

    private static final Logger log = LogManager.getLogger(PropertyService.class);

    /**
     * Passes an address string into PropertyDao. If the address string is empty, it will return an empty Optional to the controller.
     * @param address the address to be queried
     * @return an Optional ViolationsResponse
     */
    public Optional<ViolationsResponse> getViolationsResponseByAddress(String address) {
        List<Violation> violationsResult = propertyDao.findViolationsByAddress(address);
        Scofflaw scofflaw = propertyDao.findScofflawByAddress(address);

        if (scofflaw == null) {
            log.info("No scofflaws found with address= {}", address);
        }
        if (violationsResult.isEmpty()) {
            log.info("No violations found with address= {}", address);
        }

        return ViolationsMapper.mapToResponse(violationsResult, scofflaw);
    }

    /**
     * Passes a LocalDate object into PropertyDao. Returns an empty optional if no Scofflaws found.
     * @param fromDate the date to be queried (inclusive)
     * @return an Optional ScofflawsResponse
     */
    public Optional<ScofflawsResponse> getScofflawsByDate(LocalDate fromDate) {
        List<Scofflaw> scofflawList = propertyDao.findScofflawsByDate(fromDate);

        if (scofflawList.isEmpty()) {
            log.info("No scofflaws found on or after date= {}", fromDate.toString());
            return Optional.empty();
        }

        return Optional.of(new ScofflawsResponse(scofflawList));
    }

    /**
     * Passes an address and a CommentsRequest object into PropertyDao.
     * @param address the address to be inserted
     * @param request the request body to be inserted
     * @return a boolean of whether the comment was posted or not
     */
    public boolean createCommentWithAddress(String address, CommentsRequest request) {
        if (request.getComment().isEmpty()) {
            log.error("CommentRequest has an empty comment");
            return false;
        }
        if (request.getAuthor().isEmpty()) {
            log.error("CommentRequest has an empty author");
            return false;
        }

        return propertyDao.createCommentWithAddress(address, request) > 0;
    }

}
