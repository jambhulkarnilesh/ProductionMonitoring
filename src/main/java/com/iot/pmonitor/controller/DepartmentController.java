package com.iot.pmonitor.controller;

import com.iot.pmonitor.enums.DepartmentSearchEnum;
import com.iot.pmonitor.enums.PageDirection;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.DepartmentCreateRequest;
import com.iot.pmonitor.request.DepartmentUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.DepartmentService;
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
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<PMResponse> saveDepartmentDetails(@RequestBody DepartmentCreateRequest departmentCreateRequest) {
        PMResponse response = departmentService.saveDepartment(departmentCreateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PMResponse> updateDepartmentDetails(@RequestBody DepartmentUpdateRequest departmentUpdateRequest) {
        PMResponse response = departmentService.updateDepartment(departmentUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @PageableAsQueryParam
    public ResponseEntity<PMResponse> findDepartmentDetails(@RequestParam(required = false) DepartmentSearchEnum searchEnum,
                                                      @RequestParam(required = false) String searchString,
                                                      @RequestParam(required = false) StatusCdEnum statusCdEnum,
                                                      @Parameter(hidden = true) Pageable pageable,
                                                      @Parameter(hidden = true) PageDirection pageDirection,
                                                      @Parameter(hidden = true) String sortParam) {
        PMResponse response = departmentService.findDepartmentDetails(searchEnum, searchString, statusCdEnum, pageable, sortParam, PMUtils.getDirection(pageDirection));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
