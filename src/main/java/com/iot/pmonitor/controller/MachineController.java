package com.iot.pmonitor.controller;

import com.iot.pmonitor.enums.MachineSearchEnum;
import com.iot.pmonitor.enums.PageDirection;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.MachineCreateRequest;
import com.iot.pmonitor.request.MachineUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.MachineService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @PostMapping
    public ResponseEntity<PMResponse> saveMachineDetails(@RequestBody MachineCreateRequest machineRequest) {
        PMResponse response = machineService.saveMachine(machineRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PMResponse> updateMachineDetails(@RequestBody MachineUpdateRequest machineUpdateRequest) {
        PMResponse response = machineService.updateMachine(machineUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @PageableAsQueryParam
    public ResponseEntity<PMResponse> getMachineDetails(@RequestParam(required = false) MachineSearchEnum searchEnum,
                                                        @RequestParam(required = false) String searchString,
                                                        @RequestParam(required = false) StatusCdEnum statusCdEnum,
                                                        @Parameter(hidden = true) Pageable pageable,
                                                        @Parameter(hidden = true) PageDirection pageDirection,
                                                        @Parameter(hidden = true) String sortParam) {
        PMResponse response = machineService.findMachineDetails(searchEnum, searchString, statusCdEnum, pageable, sortParam, PMUtils.getDirection(pageDirection));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
