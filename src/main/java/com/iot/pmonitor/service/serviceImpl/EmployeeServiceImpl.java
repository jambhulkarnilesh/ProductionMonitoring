package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.DepartmentEntity;
import com.iot.pmonitor.entity.EmployeeAudit;
import com.iot.pmonitor.entity.EmployeeEntity;
import com.iot.pmonitor.entity.EmployeeLoginAudit;
import com.iot.pmonitor.entity.EmployeeLoginEntity;
import com.iot.pmonitor.enums.EmployeeSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.EmployeeAuditRepo;
import com.iot.pmonitor.repository.EmployeeLoginAuditRepo;
import com.iot.pmonitor.repository.EmployeeLoginRepo;
import com.iot.pmonitor.repository.EmployeeRepo;
import com.iot.pmonitor.request.EmployeeCreateRequest;
import com.iot.pmonitor.request.EmployeeUpdateRequest;
import com.iot.pmonitor.response.DepartmentReponse;
import com.iot.pmonitor.response.EmployeeResponse;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.EmployeeService;
import com.iot.pmonitor.utils.PMUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeAuditRepo employeeAuditRepo;

    @Autowired
    private EmployeeLoginRepo employeeLoginRepo;

    @Autowired
    private EmployeeLoginAuditRepo employeeLoginAuditRepo;


    @Override
    public PMResponse saveEmployee(EmployeeCreateRequest employeeCreateRequest) {

        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepo.findByEmpMobileNoOrEmailIdEqualsIgnoreCase(employeeCreateRequest.getEmpMobileNo(), employeeCreateRequest.getEmailId());
        if(optionalEmployeeEntity.isPresent()){
            log.error("Inside EmployeeServiceImpl >> saveEmployee()");
            throw new PMException("EmployeeServiceImpl", false, "Employee Mobile number or email id already exist");
        }


        EmployeeEntity employeeEntity = convertEmployeeCreateRequestToEntity(employeeCreateRequest);
        try {
            employeeRepo.save(employeeEntity);
            EmployeeAudit employeeAudit = new EmployeeAudit(employeeEntity);
            employeeAuditRepo.save(employeeAudit);

            EmployeeLoginEntity employeeLoginEntity = convertRequestToEmployeeLogin(employeeCreateRequest);
            employeeLoginRepo.save(employeeLoginEntity);
            EmployeeLoginAudit employeeLoginAudit = new EmployeeLoginAudit(employeeLoginEntity);
            employeeLoginAuditRepo.save(employeeLoginAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside EmployeeServiceImpl >> saveEmployee()");
            throw new PMException("EmployeeServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse updateEmployee(EmployeeUpdateRequest employeeUpdateRequest) {
        EmployeeEntity employeeEntity = convertEmployeeUpdateRequestToEntity(employeeUpdateRequest);
        try {
            employeeRepo.save(employeeEntity);
            EmployeeAudit employeeAudit = new EmployeeAudit(employeeEntity);
            employeeAuditRepo.save(employeeAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_UPDATE)
                    .build();
        } catch (Exception ex) {
            log.error("Inside EmployeeServiceImpl >> updateEmployee()");
            throw new PMException("EmployeeServiceImpl", false, ex.getMessage());
        }
    }


    @Override
    public PMResponse findEmployee(EmployeeSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<EmployeeEntity> partEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                partEntities = employeeRepo.findByEmpIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_NAME":
                partEntities = employeeRepo.findByEmpFirstNameAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_DEPT_ID":
                partEntities = employeeRepo.findByDeptIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_DESIG_ID":
                partEntities = employeeRepo.findByDesigIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_ROLE_ID":
                partEntities = employeeRepo.findByRoleIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_MOBILE_NO":
                partEntities = employeeRepo.findByEmpMobileNoAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_GENDER":
                partEntities = employeeRepo.findByEmpGenderAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_STATUS":
                partEntities = employeeRepo.findByStatusCd(statusCdEnum.getSearchType(), pageable);
                break;
            case "ALL":
            default:
                partEntities = employeeRepo.findAll(pageable);
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(partEntities)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    @Override
    public List<EmployeeResponse> findAllEmployeeDetails() {
        List<EmployeeEntity> employeeEntities =  employeeRepo.findAllEmployeeDetails();
        List<EmployeeResponse>  employeeResponses= new ArrayList<>();
        EmployeeResponse employeeResponse = null;
        for(EmployeeEntity employeeEntity : employeeEntities){
            employeeResponse = new EmployeeResponse();
            employeeResponse.setEmpId(employeeEntity.getEmpId());
            employeeResponse.setEmpFullName(employeeEntity.getEmpFirstName() +" "+employeeEntity.getEmpMiddleName() +" "+employeeEntity.getEmpLastName());
            employeeResponse.setStatusCd(employeeEntity.getStatusCd());
            employeeResponses.add(employeeResponse);
        }
        return employeeResponses;
    }

    private EmployeeLoginEntity convertRequestToEmployeeLogin(EmployeeCreateRequest employeeCreateRequest) {
        return EmployeeLoginEntity.employeeLoginEntityBuilder()
                .empMobileNo(employeeCreateRequest.getEmpMobileNo())
                .emailId(employeeCreateRequest.getEmailId())
                .roleId(employeeCreateRequest.getRoleId())
                .empPassword(employeeCreateRequest.getEmpMobileNo())
                .remark(employeeCreateRequest.getRemark())
                .statusCd(employeeCreateRequest.getStatusCd())
                .createdUserId(employeeCreateRequest.getEmployeeId())
                .build();
    }

    private EmployeeEntity convertEmployeeCreateRequestToEntity(EmployeeCreateRequest employeeCreateRequest) {
        return EmployeeEntity.employeeEntityBuilder()
                .depId(employeeCreateRequest.getDepId())
                .desigId(employeeCreateRequest.getDesigId())
                .roleId(employeeCreateRequest.getRoleId())
                .empFirstName(employeeCreateRequest.getEmpFirstName())
                .empMiddleName(employeeCreateRequest.getEmpMiddleName())
                .empLastName(employeeCreateRequest.getEmpLastName())
                .empDob(employeeCreateRequest.getEmpDob())
                .empMobileNo(employeeCreateRequest.getEmpMobileNo())
                .empEmerMobileNo(employeeCreateRequest.getEmpEmerMobileNo())
                .empPhoto(employeeCreateRequest.getEmpPhoto())
                .emailId(employeeCreateRequest.getEmailId())
                .tempAddress(employeeCreateRequest.getTempAddress())
                .permAddress(employeeCreateRequest.getPermAddress())
                .empGender(employeeCreateRequest.getEmpGender())
                .empBloodgroup(employeeCreateRequest.getEmpBloodgroup())
                .remark(employeeCreateRequest.getRemark())
                .statusCd(employeeCreateRequest.getStatusCd())
                .createdUserId(employeeCreateRequest.getEmployeeId())
                .build();
    }

    private EmployeeEntity convertEmployeeUpdateRequestToEntity(EmployeeUpdateRequest employeeUpdateRequest) {
        return EmployeeEntity.employeeEntityBuilder()
                .empId(employeeUpdateRequest.getEmpId())
                .depId(employeeUpdateRequest.getDepId())
                .desigId(employeeUpdateRequest.getDesigId())
                .roleId(employeeUpdateRequest.getRoleId())
                .empFirstName(employeeUpdateRequest.getEmpFirstName())
                .empMiddleName(employeeUpdateRequest.getEmpMiddleName())
                .empLastName(employeeUpdateRequest.getEmpLastName())
                .empDob(employeeUpdateRequest.getEmpDob())
                .empMobileNo(employeeUpdateRequest.getEmpMobileNo())
                .empEmerMobileNo(employeeUpdateRequest.getEmpEmerMobileNo())
                .empPhoto(employeeUpdateRequest.getEmpPhoto())
                .emailId(employeeUpdateRequest.getEmailId())
                .tempAddress(employeeUpdateRequest.getTempAddress())
                .permAddress(employeeUpdateRequest.getPermAddress())
                .empGender(employeeUpdateRequest.getEmpGender())
                .empBloodgroup(employeeUpdateRequest.getEmpBloodgroup())
                .remark(employeeUpdateRequest.getRemark())
                .statusCd(employeeUpdateRequest.getStatusCd())
                .createdUserId(getCreatedEmployeeName(employeeUpdateRequest.getEmpId()))
                .createdDate(getCreatedDateTime(employeeUpdateRequest.getEmpId()))
                .updatedUserId(employeeUpdateRequest.getEmployeeId())
                .build();
    }

    private String getCreatedEmployeeName(Integer empId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(empId);
        if (employeeEntity.isPresent()) {
            return employeeEntity.get().getCreatedUserId();
        }
        return null;
    }

    private Instant getCreatedDateTime(Integer empId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(empId);
        if (employeeEntity.isPresent()) {
            return employeeEntity.get().getCreatedDate();
        }
        return null;
    }

}
