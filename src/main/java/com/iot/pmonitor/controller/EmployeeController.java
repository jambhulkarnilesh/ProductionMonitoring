package com.iot.pmonitor.controller;

import com.iot.pmonitor.enums.EmployeeSearchEnum;
import com.iot.pmonitor.enums.PageDirection;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.EmployeeCreateRequest;
import com.iot.pmonitor.request.EmployeeUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.EmployeeService;
import com.iot.pmonitor.utils.PMUtils;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<PMResponse> saveEmployee(@RequestBody EmployeeCreateRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeRequest));
    }

    @PutMapping
    public ResponseEntity<PMResponse> updateEmployee(@RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeUpdateRequest));
    }

    @GetMapping(value = "/search")
    @PageableAsQueryParam
    public ResponseEntity<PMResponse> getAllEmployee(@RequestParam(required = false) EmployeeSearchEnum searchEnum,
                                                     @RequestParam(required = false) String searchString,
                                                     @RequestParam(required = false) StatusCdEnum statusCdEnum,
                                                     @Parameter(hidden = true) Pageable pageable,
                                                     @Parameter(hidden = true) PageDirection pageDirection,
                                                     @Parameter(hidden = true) String sortParam) {
        PMResponse response = employeeService.findEmployee(searchEnum, searchString, statusCdEnum, pageable, sortParam, PMUtils.getDirection(pageDirection));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
