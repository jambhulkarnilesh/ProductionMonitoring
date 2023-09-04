package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class RecipesCreateRequest {

    @Schema(example = "2023-09-01", description = "This field is used for recipes date")
    private LocalDate recepDate;

    @Schema(example = "1", description = "This field is used for employee id")
    private int empId;

    @Schema(example = "1", description = "This field is used for machine id")
    private Integer machineId;

    @Schema(example = "1", description = "This field is used for part id")
    private Integer partId;

    @Schema(example = "1000", description = "This field is used for machine target count")
    private String machTargetJobCount;

    @Schema(example = "This is for future", description = "This field is used for remark")
    private String remark;

    @Schema(example = "pending / completed", description = "This field is used for maintain recipes status")
    private String recepStatus;

    @Schema(example = "A", description = "This field is used for maintain  status")
    private String statusCd;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String employeeId;
}
