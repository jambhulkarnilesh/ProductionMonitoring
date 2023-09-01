package com.iot.pmonitor.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PMSearchRequest {
    private String fromDate;
    private String toDate;
    private Integer machineId;
    private String machineStatus;
    private String machineName;
    private String machinePLCType;
    private Integer partId;
    private String partName;
    private String machTargetJobCount;
    private String machCompletedJobCount;
    private String machJobStatus;
}
