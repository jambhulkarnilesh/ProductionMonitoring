package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PartCreateRequest {

    @Schema(example = "Nozzel", description = "This field is used for Part Name")
    private String partName;

    @Schema(example = "1000", description = "This field is used to set job part target")
    private String partJobTarget;

    @Schema(example = "0", description = "This field is used to set assigned job target")
    private String partJobAssigned;

    @Schema(example = "This is request remark", description = "This field is used for Machine maximum capacity to produce")
    private String remark;

    @Schema(example = "A", description = "This field is used for Status i.e A or I")
    private String statusCd;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String employeeId;
}
