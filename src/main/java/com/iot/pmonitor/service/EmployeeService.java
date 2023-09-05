package com.iot.pmonitor.service;

import com.iot.pmonitor.enums.EmployeeSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.EmployeeCreateRequest;
import com.iot.pmonitor.request.EmployeeUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    public PMResponse saveEmployee(EmployeeCreateRequest employeeRequest);

    public PMResponse updateEmployee(EmployeeUpdateRequest employeeUpdateRequest);
    public PMResponse findEmployee(EmployeeSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable pageable, String sortParam, String pageDirection );
}
