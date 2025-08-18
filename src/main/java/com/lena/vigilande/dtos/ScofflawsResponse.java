package com.lena.vigilande.dtos;

import com.lena.vigilande.pojos.Scofflaw;

import java.util.List;

public class ScofflawsResponse {
    private List<Scofflaw> scofflaws;

    public ScofflawsResponse(List<Scofflaw> scofflaws) {
        this.scofflaws = scofflaws;
    }

    public List<Scofflaw> getScofflaws() {
        return scofflaws;
    }
}
