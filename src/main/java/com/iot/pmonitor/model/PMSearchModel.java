package com.iot.pmonitor.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@Builder
public class PMSearchModel {

    private Pageable pageable;
    private Integer pageSize;
    private Integer pageOffset;
    private String sortName;
    private String sortDirection;
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
