package com.iot.pmonitor.response;

import lombok.Data;

@Data
public class MachineResponse {

    private Integer machineId;
    private String machineName;
    private String statusCd;
}
