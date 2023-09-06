package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PMSearchRequest {

    @Schema(example = "1", description = "This field is used for Machine Id")
    private Integer machineId;

    @Schema(example = "1", description = "This field is used for Part Id")
    private Integer partId;

    @Schema(example = "1", description = "This field is used for machine target job count")
    private String machTargetJobCount;

    @Schema(example = "1", description = "This field is used for Part Id")
    private String machJobStatus;
}
