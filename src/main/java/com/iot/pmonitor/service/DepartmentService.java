package com.iot.pmonitor.service;

import com.iot.pmonitor.entity.DepartmentEntity;
import com.iot.pmonitor.enums.DepartmentSearchEnum;
import com.iot.pmonitor.enums.RoleSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.DepartmentCreateRequest;
import com.iot.pmonitor.request.DepartmentUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    public PMResponse saveDepartment(DepartmentCreateRequest departmentCreateRequest);

    public PMResponse updateDepartment(DepartmentUpdateRequest departmentUpdateRequest);

    public PMResponse findDepartmentDetails(DepartmentSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable pageable, String sortParam, String pageDirection );

    public List<DepartmentEntity> findAllDepartmentDetails();

}
