package com.iot.pmonitor.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PMResponse {

    private String responseMessage;
    private boolean isSuccess;
    private Object responseData;
}
