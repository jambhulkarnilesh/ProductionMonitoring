package com.iot.pmonitor.controller;

import com.iot.pmonitor.request.MachineRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.MachineService;
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
@RequestMapping(value = "/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @PostMapping
    public ResponseEntity saveMachineDetails(@RequestBody MachineRequest machineRequest) {
        PMResponse response = machineService.saveMachine(machineRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
