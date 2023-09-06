package com.iot.pmonitor.enums;

public enum PartSearchEnum {

    ALL("ALL"),
    BY_ID("BY_ID"),
    BY_NAME("BY_NAME"),
    BY_JOB_TARGET("BY_JOB_TARGET"),
    BY_STATUS("BY_STATUS");

    private String searchBy;
    PartSearchEnum(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchType() {
        return this.searchBy;
    }
}
