package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineRequest {
    @Schema(example = "Lumenious", description = "This field is used for Machine name")
    private String machineName;

    @Schema(example = "123.22.33.222", description = "This field is used for Machine IP Addrress")
    private String machineIpAddress;

    @Schema(example = "3500", description = "This field is used for Machine Port Number")
    private String machinePortNo;

    @Schema(example = "PLC", description = "This field is used for Machine PLC Type")
    private String machinePLCType;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String createdUserId;
}
