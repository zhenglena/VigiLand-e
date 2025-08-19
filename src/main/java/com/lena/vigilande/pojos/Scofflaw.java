package com.lena.vigilande.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class Scofflaw {
    private String address;

    @JsonProperty("building_list_date")
    private LocalDate buildingListDate;

    public Scofflaw(String address, LocalDate buildingListDate) {
        this.address = address;
        this.buildingListDate = buildingListDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBuildingListDate() {
        return buildingListDate;
    }

    public void setBuildingListDate(LocalDate buildingListDate) {
        this.buildingListDate = buildingListDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scofflaw scofflaw = (Scofflaw) o;
        return Objects.equals(getAddress(), scofflaw.getAddress()) && Objects.equals(getBuildingListDate(), scofflaw.getBuildingListDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getBuildingListDate());
    }
}
