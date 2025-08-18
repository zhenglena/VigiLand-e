package com.lena.vigilande.dtos;

import java.util.List;

public class ScofflawsResponse {
    private List<String> addresses;

    public ScofflawsResponse(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getAddresses() {
        return addresses;
    }
}
