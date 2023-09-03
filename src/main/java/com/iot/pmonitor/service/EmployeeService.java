package com.iot.pmonitor.service;

import com.iot.pmonitor.entity.EmployeeEntity;
import com.iot.pmonitor.enums.SearchEnum;
import com.iot.pmonitor.request.EmployeeCreateRequest;
import com.iot.pmonitor.request.EmployeeUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    public PMResponse saveEmployee(EmployeeCreateRequest employeeRequest);

    public PMResponse updateEmployee(EmployeeUpdateRequest employeeUpdateRequest);
    public PMResponse findEmployee(SearchEnum searchEnum, String searchString, Pageable pageable, String sortParam, String pageDirection );
}
