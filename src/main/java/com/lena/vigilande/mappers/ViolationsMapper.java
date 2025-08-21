package com.lena.vigilande.mappers;

import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;

import java.util.List;
import java.util.Optional;

/**
 * Mapper class to map data from GET endpoint into a ViolationsResponse object.
 */
public class ViolationsMapper {
    public static Optional<ViolationsResponse> mapToResponse(List<Violation> violationList, Scofflaw scofflaw) {
        boolean scofflawStatus = scofflaw != null;
        if (violationList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new ViolationsResponse(
                violationList.get(0).getViolationDate(),
                violationList.size(),
                violationList,
                scofflawStatus
        ));
    }
}
