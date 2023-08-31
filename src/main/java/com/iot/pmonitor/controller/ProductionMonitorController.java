package com.iot.pmonitor.controller;

import com.iot.pmonitor.request.ProductionMonitorRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.response.ProductionHeaderResponse;
import com.iot.pmonitor.service.ProductionMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/production-monitor")
public class ProductionMonitorController {

    @Autowired
    private ProductionMonitorService headerService;

    @PostMapping
    public ResponseEntity saveProductionHeaderDetails(@RequestBody ProductionMonitorRequest headerRequest) {
        PMResponse response = headerService.saveProductionMonitorDetails(headerRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<ProductionHeaderResponse>> getProductionHeaderDetails(){
        return new ResponseEntity<>(headerService.getProductionMonitorDetails(), HttpStatus.OK);
    }
}
