package com.iot.pmonitor.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PMLiveResponse {

    private Integer machineId;
    private String machineName;
    private String machineStatus;
    private Integer partId;
    private String partName;
    private String machTargetJobCount;
    private String machCompletedJobCount;
    private String machBalanceJobCount;

    public PMLiveResponse(Object[] objects) {

        this.machineId = Objects.nonNull(objects[0]) ? Integer.parseInt(String.valueOf(objects[0])) : null;
        this.machineName = String.valueOf(objects[1]);
        this.machineStatus = String.valueOf(objects[2]);
        this.partId = Objects.nonNull(objects[3]) ? Integer.parseInt(String.valueOf(objects[3])) : null;
        this.partName = String.valueOf(objects[4]);
        this.machTargetJobCount = String.valueOf(objects[5]);
        this.machCompletedJobCount = String.valueOf(objects[6]);
        this.machBalanceJobCount = String.valueOf(Integer.parseInt(String.valueOf(objects[5])) - Integer.parseInt(String.valueOf(objects[6])));
    }
}
