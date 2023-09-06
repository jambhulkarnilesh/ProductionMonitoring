package com.iot.pmonitor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@Builder
public class ReceipesSearchModel {

    private Pageable pageable;
    private Integer pageSize;
    private Integer pageOffset;
    private String sortName;
    private String sortDirection;
    private String fromDate;
    private String toDate;
    private int empId;
    private Integer machineId;
    private Integer partId;
    private String machTargetJobCount;
    private String recepStatus;
    private String statusCd;
}
