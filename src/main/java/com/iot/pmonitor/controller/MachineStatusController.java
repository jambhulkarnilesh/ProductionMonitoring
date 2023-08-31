package com.iot.pmonitor.controller;

import com.iot.pmonitor.request.MachineStatusRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.MachineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/machine_status")
public class MachineStatusController {

    @Autowired
    private MachineStatusService machineStatusService;

    @PostMapping
    public ResponseEntity saveMachineStatusDetails(@RequestBody MachineStatusRequest machineStatusRequest) {
        PMResponse response = machineStatusService.saveMachineStatus(machineStatusRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
