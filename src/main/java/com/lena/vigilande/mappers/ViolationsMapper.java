package com.lena.vigilande.mappers;

import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;

import java.util.List;

public class ViolationsMapper {
    public static ViolationsResponse mapToResponse(List<Violation> violationList, Scofflaw scofflaw) {
        boolean scofflawStatus = scofflaw != null;
        return new ViolationsResponse(
                violationList.get(0).getViolationDate(),
                violationList.size(),
                violationList,
                scofflawStatus
        );
    }
}
