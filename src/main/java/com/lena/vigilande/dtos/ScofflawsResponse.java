package com.lena.vigilande.dtos;

import com.lena.vigilande.pojos.Scofflaw;

import java.util.List;

/**
 * Forms the response body for the GET endpoint
 */
public class ScofflawsResponse {
    private List<Scofflaw> scofflaws;

    public ScofflawsResponse(List<Scofflaw> scofflaws) {
        this.scofflaws = scofflaws;
    }

    public List<Scofflaw> getScofflaws() {
        return scofflaws;
    }
}
