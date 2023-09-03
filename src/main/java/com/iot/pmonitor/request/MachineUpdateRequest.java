package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MachineUpdateRequest {

    @Schema(example = "1", description = "This field is used for Machine id")
    private Integer machineId;

    @Schema(example = "Lumenious", description = "This field is used for Machine name")
    private String machineName;

    @Schema(example = "123.22.33.222", description = "This field is used for Machine IP Addrress")
    private String machineIpAddress;

    @Schema(example = "3500", description = "This field is used for Machine Port Number")
    private String machinePortNo;

    @Schema(example = "PLC", description = "This field is used for Machine PLC Type")
    private String machinePLCType;

    @Schema(example = "1000", description = "This field is used for Machine maximum capacity to produce")
    private String machineMaxCapacity;

    @Schema(example = "This is request remark", description = "This field is used for Machine maximum capacity to produce")
    private String remark;

    @Schema(example = "A", description = "This field is used for Status i.e A or I")
    private String statusCd;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String employeeId;
}
