package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineStatusRequest {
    @Schema(example = "Active / Fault", description = "This field is used for Machine Status Name")
    private String machineStatusName;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String createdUserId;
}
