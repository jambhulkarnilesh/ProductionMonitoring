package com.iot.pmonitor.response;

import com.iot.pmonitor.utils.DateTimeUtils;
import lombok.Data;

import java.util.Objects;

@Data
public class PMReportResponse {
    private Integer machineId;
    private String machineStatus;
    private String machineName;
    private String machineIpAddress;
    private String machinePortNo;
    private String machinePLCType;
    private Integer partId;
    private String partName;
    private String machTargetJobCount;
    private String machCompletedJobCount;
    private String machJobStatus;
    private String createdDateTime;
    private String updatedDateTime;
    private float timeDifference;


    public PMReportResponse(Object[] object) {
        this.machineId = Objects.nonNull(object[0]) ? Integer.parseInt(String.valueOf(object[0])) : null;

        this.machineName = String.valueOf(object[1]);
        this.machineIpAddress = String.valueOf(object[2]);
        this.machinePortNo = String.valueOf(object[3]);
        this.machinePLCType = String.valueOf(object[4]);
        this.partId = Objects.nonNull(object[5]) ? Integer.parseInt(String.valueOf(object[5])) : null;
        this.partName = String.valueOf(object[6]);
        this.machTargetJobCount = String.valueOf(object[7]);
        this.machCompletedJobCount = String.valueOf(object[8]);
        this.machineStatus = String.valueOf(object[9]);
        this.machJobStatus = String.valueOf(object[10]);

        this.createdDateTime = String.valueOf(object[11]);
        this.updatedDateTime = String.valueOf(object[12]);
        this.timeDifference = calculateTime(String.valueOf(object[11]), String.valueOf(object[12]));
    }

    private float calculateTime(String fromDateTime, String toDateTime) {
        Long fromDate = null != fromDateTime ? DateTimeUtils.convertStringToInstant(fromDateTime).toEpochMilli() : null;
        Long toDate = null != toDateTime ? DateTimeUtils.convertStringToInstant(toDateTime).toEpochMilli() : null;
        if (null != fromDate && null != toDate) {
            return (toDate - fromDate) / (1000 * 60);
        }
        return 0.0f;

    }
}
