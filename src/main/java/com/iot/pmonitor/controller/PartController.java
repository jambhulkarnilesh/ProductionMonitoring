package com.iot.pmonitor.controller;

import com.iot.pmonitor.request.PartRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.PartService;
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
@RequestMapping(value = "/part")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping
    public ResponseEntity savePartDetails(@RequestBody PartRequest partRequest) {
        PMResponse response = partService.savePart(partRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
