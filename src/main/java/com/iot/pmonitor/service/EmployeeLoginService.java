package com.iot.pmonitor.service;

import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.response.PMResponse;

public interface EmployeeLoginService {


    public PMResponse updateEmployeeLogin(PartUpdateRequest partUpdateRequest);

    public PMResponse employeeLogin(String userName, String userPassword, Integer roleId);
}
