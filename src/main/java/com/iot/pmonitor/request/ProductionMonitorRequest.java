package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionMonitorRequest {

    @Schema(example = "1", description = "This field is used for Mcahine Id")
    private Integer machineId;

    @Schema(example = "Runnning / Idle / Fault", description = "This field is used for Machine Status Id")
    private String machineStatus;

    @Schema(example = "1", description = "This field is used for Part Id")
    private Integer partId;

    @Schema(example = "100", description = "This field is used for Machine Job Count")
    private String machTargetJobCount;

    @Schema(example = "0", description = "This field is used for Machine Remaining Count")
    private String machCompletedJobCount;

    @Schema(example = "No", description = "This field is used for Status i.e Yes or No")
    private String status;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String createdUserId;
}
