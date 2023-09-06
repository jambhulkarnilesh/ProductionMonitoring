package com.iot.pmonitor.controller;

import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.EmployeeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/login")
public class EmployeeLoginController {

    @Autowired
    private EmployeeLoginService employeeLoginService;

    @GetMapping
    public ResponseEntity<PMResponse> findPartDetails(@RequestParam(required = false) String userName,

                                                      @RequestParam(required = false) String userPassword
    ) {
        PMResponse response = employeeLoginService.employeeLogin(userName, userPassword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
