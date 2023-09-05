package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.entity.EmployeeLoginEntity;
import com.iot.pmonitor.repository.EmployeeLoginAuditRepo;
import com.iot.pmonitor.repository.EmployeeLoginRepo;
import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.EmployeeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeLoginServiceImpl implements EmployeeLoginService {

    @Autowired
    private EmployeeLoginRepo employeeLoginRepo;

    @Autowired
    private EmployeeLoginAuditRepo employeeLoginAuditRepo;

    @Override
    public PMResponse updateEmployeeLogin(PartUpdateRequest partUpdateRequest) {
        return null;
    }

    @Override
    public PMResponse employeeLogin(String userName, String userPassword, Integer roleId) {
        Optional<EmployeeLoginEntity> employeeLoginEntity = employeeLoginRepo.findByEmpMobileNoAndEmpPasswordAndRoleIdAndStatusCd(userName, userPassword, roleId,"A");
        if (employeeLoginEntity.isPresent()) {
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage("Login successfully")
                    .build();
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseMessage("User name or password is wrong")
                .build();
    }
}
