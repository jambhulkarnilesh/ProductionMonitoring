package com.iot.pmonitor.controller;

import com.iot.pmonitor.enums.PageDirection;
import com.iot.pmonitor.enums.PartSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.PartCreateRequest;
import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.PartService;
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
@RequestMapping(value = "/part")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping
    public ResponseEntity<PMResponse> savePartDetails(@RequestBody PartCreateRequest partCreateRequest) {
        PMResponse response = partService.savePart(partCreateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PMResponse> updatePartDetails(@RequestBody PartUpdateRequest partUpdateRequest) {
        PMResponse response = partService.updatePart(partUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @PageableAsQueryParam
    public ResponseEntity<PMResponse> findPartDetails(@RequestParam(required = false) PartSearchEnum searchEnum,
                                         @RequestParam(required = false) String searchString,
                                         @RequestParam(required = false) StatusCdEnum statusCdEnum,
                                         @Parameter(hidden = true) Pageable pageable,
                                         @Parameter(hidden = true) PageDirection pageDirection,
                                         @Parameter(hidden = true) String sortParam) {
        PMResponse response = partService.findPartDetails(searchEnum, searchString, statusCdEnum, pageable, sortParam, PMUtils.getDirection(pageDirection));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
