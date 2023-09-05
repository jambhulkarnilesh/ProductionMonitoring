package com.iot.pmonitor.controller;

import com.iot.pmonitor.enums.DesignationSearchEnum;
import com.iot.pmonitor.enums.PageDirection;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.DesignationCreateRequest;
import com.iot.pmonitor.request.DesignationUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.DesignationService;
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
@RequestMapping(value = "/designation")
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @PostMapping
    public ResponseEntity<PMResponse> saveDesignationDetails(@RequestBody DesignationCreateRequest designationCreateRequest) {
        PMResponse response = designationService.saveDesignation(designationCreateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PMResponse> updateDesignationDetails(@RequestBody DesignationUpdateRequest designationUpdateRequest) {
        PMResponse response = designationService.updateDesignation(designationUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    @PageableAsQueryParam
    public ResponseEntity<PMResponse> findDesignationDetails(@RequestParam(required = false) DesignationSearchEnum searchEnum,
                                                            @RequestParam(required = false) String searchString,
                                                            @RequestParam(required = false) StatusCdEnum statusCdEnum,
                                                            @Parameter(hidden = true) Pageable pageable,
                                                            @Parameter(hidden = true) PageDirection pageDirection,
                                                            @Parameter(hidden = true) String sortParam) {
        PMResponse response = designationService.findDesignationDetails(searchEnum, searchString, statusCdEnum, pageable, sortParam, PMUtils.getDirection(pageDirection));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}