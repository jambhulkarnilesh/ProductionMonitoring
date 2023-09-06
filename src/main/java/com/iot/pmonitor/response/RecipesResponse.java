package com.iot.pmonitor.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RecipesResponse {

    private Integer recepId;
    private LocalDate recepDate;
    private int empId;
    private String empFullName;
    private Integer machineId;
    private String machineName;
    private Integer partId;
    private String partName;
    private String machTargetJobCount;
    private String recepStatus;
    private String statusCd;
}
