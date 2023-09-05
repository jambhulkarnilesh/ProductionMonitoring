package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.entity.EmployeeEntity;
import com.iot.pmonitor.entity.EmployeeLoginEntity;
import com.iot.pmonitor.repository.EmployeeLoginAuditRepo;
import com.iot.pmonitor.repository.EmployeeLoginRepo;
import com.iot.pmonitor.repository.EmployeeRepo;
import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.response.LoginResponse;
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

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public PMResponse updateEmployeeLogin(PartUpdateRequest partUpdateRequest) {
        return null;
    }

    @Override
    public PMResponse employeeLogin(String userName, String userPassword) {

        Optional<EmployeeLoginEntity> employeeLoginEntity = employeeLoginRepo.findByEmpMobileNoAndEmpPasswordAndStatusCd(userName, userPassword, "A");
        String empMbNo = null;
        if (employeeLoginEntity.isPresent()) {
            empMbNo = employeeLoginEntity.get().getEmpMobileNo();
            EmployeeEntity employeeEntity = employeeRepo.findByEmpMobileNoAndStatusCd(empMbNo, "A");
            LoginResponse loginResponse = LoginResponse.builder()
                    .empId(employeeEntity.getEmpId())
                    .empFirstName(employeeEntity.getEmpFirstName())
                    .empMiddleName(employeeEntity.getEmpMiddleName())
                    .empLastName(employeeEntity.getEmpLastName())
                    .roleId(employeeEntity.getRoleId())
                    .empMobileNo(employeeEntity.getEmpMobileNo())
                    .emailId(employeeEntity.getEmailId())
                    .build();
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseData(loginResponse)
                    .responseMessage("Login successfully")
                    .build();
        }
        return PMResponse.builder()
                .isSuccess(false)
                .responseMessage("User name or password is wrong")
                .build();
    }
}
