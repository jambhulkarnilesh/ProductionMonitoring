package com.iot.pmonitor.response;

import lombok.Data;

@Data
public class DesignationReponse {

    private Integer desigId;
    private Integer deptId;
    private String deptName;
    private String desigName;
    private String statusCd;
}
