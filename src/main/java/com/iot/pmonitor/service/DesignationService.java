package com.iot.pmonitor.service;

import com.iot.pmonitor.enums.DepartmentSearchEnum;
import com.iot.pmonitor.enums.DesignationSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.DesignationCreateRequest;
import com.iot.pmonitor.request.DesignationUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

public interface DesignationService {

    public PMResponse saveDesignation(DesignationCreateRequest departmentCreateRequest);

    public PMResponse updateDesignation(DesignationUpdateRequest departmentUpdateRequest);


    public PMResponse findDesignationDetails(DesignationSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable pageable, String sortParam, String pageDirection );

}
