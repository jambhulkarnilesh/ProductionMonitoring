package com.iot.pmonitor.enums;

public enum SearchEnum {
    ALL("ALL"),
    BY_ID("BY_ID"),
    BY_NAME("BY_NAME"),
    BY_STATUS("BY_STATUS");

    private String searchBy;
    SearchEnum(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchType() {
        return this.searchBy;
    }
}
