package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductionMonitorRequest {

    @Schema(example = "1", description = "This field is used for Mcahine Id")
    private Integer machineId;

    @Schema(example = "1", description = "This field is used for Machine Status Id")
    private Integer machineStatusId;

    @Schema(example = "1", description = "This field is used for Part Id")
    private Integer partId;

    @Schema(example = "100", description = "This field is used for Machine Job Count")
    private String machTargetJobCount;

    @Schema(example = "0", description = "This field is used for Machine Remaining Count")
    private String machCompletedJobCount;

    @Schema(example = "Pending", description = "This field is used for Machine job status")
    private String machJobStatus;

    @Schema(example = "This is request remark", description = "This field is used for Machine maximum capacity to produce")
    private String remark;
    
    @Schema(example = "A", description = "This field is used for Status i.e A or I")
    private String statusCd;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String employeeId;
}
