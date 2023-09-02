package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartRequest {

    @Schema(example = "1", description = "This field is used for Part Id")
    private Integer partId;

    @Schema(example = "Nozzel", description = "This field is used for Part Name")
    private String partName;

    @Schema(example = "1000", description = "This field is used to set job part target")
    private String partJobTarget;

    @Schema(example = "0", description = "This field is used to set assigned job target")
    private String partJobAssigned;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String createdUserId;
}
