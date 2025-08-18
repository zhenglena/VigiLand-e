package com.lena.vigilande.pojos;

import java.time.LocalDate;

public class Scofflaw {
    private String address;
    private String recordId;
    private LocalDate buildingListDate;

    public Scofflaw(String address, String recordId, LocalDate buildingListDate) {
        this.address = address;
        this.recordId = recordId;
        this.buildingListDate = buildingListDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public LocalDate getBuildingListDate() {
        return buildingListDate;
    }

    public void setBuildingListDate(LocalDate buildingListDate) {
        this.buildingListDate = buildingListDate;
    }
}
