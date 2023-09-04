package com.iot.pmonitor.controller;

import com.iot.pmonitor.enums.PageDirection;
import com.iot.pmonitor.model.ReceipesSearchModel;
import com.iot.pmonitor.request.ReceipesSearchRequest;
import com.iot.pmonitor.request.RecipesCreateRequest;
import com.iot.pmonitor.request.RecipesUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.RecipesService;
import com.iot.pmonitor.utils.PMUtils;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/receipes")
public class ReceipesController {

    @Autowired
    private RecipesService recipesService;

    @PostMapping
    public ResponseEntity<PMResponse> saveRecipes(@RequestBody RecipesCreateRequest recipesCreateRequest) {
        PMResponse response = recipesService.saveRecipes(recipesCreateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PMResponse> updateRecipes(@RequestBody RecipesUpdateRequest recipesUpdateRequest) {
        PMResponse response = recipesService.updateRecipes(recipesUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/search")
    @PageableAsQueryParam
    public ResponseEntity<PMResponse> findRecipesDetails(@Valid @RequestBody ReceipesSearchRequest receipesSearchRequest,
                                                         @Parameter(hidden = true) Pageable pageable,
                                                         @Parameter(hidden = true) PageDirection pageDirection,
                                                         @Parameter(hidden = true) String sortParam) {
        ReceipesSearchModel receipesSearchModel = ReceipesSearchModel.builder()
                .fromDate(receipesSearchRequest.getFromDate())
                .toDate(receipesSearchRequest.getToDate())
                .empId(receipesSearchRequest.getEmpId())
                .machineId(receipesSearchRequest.getMachineId())
                .partId(receipesSearchRequest.getPartId())
                .machTargetJobCount(receipesSearchRequest.getMachTargetJobCount())
                .recepStatus(receipesSearchRequest.getRecepStatus())
                .statusCd(receipesSearchRequest.getStatusCd())
                .pageable(pageable)
                .sortDirection(PMUtils.getDirection(pageDirection))
                .sortName(sortParam)
                .build();
        return new ResponseEntity<>(recipesService.findReceipesDetails(receipesSearchModel), HttpStatus.OK);

    }
}
