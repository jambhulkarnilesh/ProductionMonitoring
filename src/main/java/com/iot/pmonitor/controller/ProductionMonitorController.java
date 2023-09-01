package com.iot.pmonitor.controller;

import com.iot.pmonitor.enums.PageDirection;
import com.iot.pmonitor.request.PMSearch;
import com.iot.pmonitor.request.PMSearchRequest;
import com.iot.pmonitor.request.ProductionMonitorRequest;
import com.iot.pmonitor.response.PMLiveResponse;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.ProductionMonitorService;
import com.iot.pmonitor.utils.PMUtils;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/production-monitor")
public class ProductionMonitorController {

    @Autowired
    private ProductionMonitorService monitorService;

    @PostMapping
    public ResponseEntity savePMDetails(@RequestBody ProductionMonitorRequest headerRequest) {
        return new ResponseEntity<>(monitorService.savePMDetails(headerRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/live")
    public ResponseEntity<List<PMLiveResponse>> getPMLiveDetails() {
        return new ResponseEntity<>(monitorService.getLivePMDetails(), HttpStatus.OK);
    }

    @PostMapping(value = "/search")
    @PageableAsQueryParam
    public ResponseEntity getPartDetails(@Valid @RequestBody PMSearchRequest pmSearchRequest,
                                         @Parameter(hidden = true) Pageable pageable,
                                         @Parameter(hidden = true) PageDirection pageDirection,
                                         @Parameter(hidden = true) String sortParam) {
        PMSearch pmSearchCriteria = PMSearch.builder()
                .fromDate(pmSearchRequest.getFromDate())
                .toDate(pmSearchRequest.getToDate())
                .machineId(pmSearchRequest.getMachineId())
                .machineStatus(pmSearchRequest.getMachineStatus())
                .machineName(pmSearchRequest.getMachineName())
                .machinePLCType(pmSearchRequest.getMachinePLCType())
                .partId(pmSearchRequest.getPartId())
                .partName(pmSearchRequest.getPartName())
                .machTargetJobCount(pmSearchRequest.getMachTargetJobCount())
                .machCompletedJobCount(pmSearchRequest.getMachCompletedJobCount())
                .machJobStatus(pmSearchRequest.getMachJobStatus())

                .pageable(pageable)
                .sortDirection(PMUtils.getDirection(pageDirection))
                .sortName(sortParam)

                .build();
        PMResponse response = monitorService.findPMDetails(pmSearchCriteria);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
