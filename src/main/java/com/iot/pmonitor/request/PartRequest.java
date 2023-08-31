package com.iot.pmonitor.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartRequest {
    @Schema(example = "Nozzel", description = "This field is used for Part Name")
    private String partName;

    @Schema(example = "PM", description = "This field is used for Created User Id")
    private String createdUserId;
}
