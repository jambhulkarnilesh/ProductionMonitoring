package com.iot.pmonitor.response;

import lombok.Builder;
import lombok.Data;

@Data
public class EmployeeResponse {

    private Integer empId;
    private String empFullName;
    private String statusCd;
}
