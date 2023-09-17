package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.DepartmentAudit;
import com.iot.pmonitor.entity.DepartmentEntity;
import com.iot.pmonitor.enums.DepartmentSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.DepartmentAuditRepo;
import com.iot.pmonitor.repository.DepartmentRepo;
import com.iot.pmonitor.request.DepartmentCreateRequest;
import com.iot.pmonitor.request.DepartmentUpdateRequest;
import com.iot.pmonitor.response.DepartmentReponse;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.DepartmentService;
import com.iot.pmonitor.utils.PMUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private DepartmentAuditRepo departmentAuditRepo;

    @Override
    public PMResponse saveDepartment(DepartmentCreateRequest departmentCreateRequest) {

        Optional<DepartmentEntity> optionalDepartmentEntity = departmentRepo.findByDeptNameEqualsIgnoreCase(departmentCreateRequest.getDeptName());
        if(optionalDepartmentEntity.isPresent()){
            log.error("Inside DepartmentServiceImpl >> saveDepartment()");
            throw new PMException("DepartmentServiceImpl", false, "Department name already exist");
        }

        DepartmentEntity departmentEntity = convertDepartmentCreateRequestToEntity(departmentCreateRequest);
        try {
            departmentRepo.save(departmentEntity);
            DepartmentAudit partAudit = new DepartmentAudit(departmentEntity);
            departmentAuditRepo.save(partAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside DepartmentServiceImpl >> saveDepartment()");
            throw new PMException("DepartmentServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse updateDepartment(DepartmentUpdateRequest departmentUpdateRequest) {
        DepartmentEntity departmentEntity = convertDepartmentUpdateRequestToEntity(departmentUpdateRequest);
        try {
            departmentRepo.save(departmentEntity);
            DepartmentAudit departmentAudit = new DepartmentAudit(departmentEntity);
            departmentAuditRepo.save(departmentAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_UPDATE)
                    .build();
        } catch (Exception ex) {
            log.error("Inside DepartmentServiceImpl >> updateDepartment()");
            throw new PMException("DepartmentServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse findDepartmentDetails(DepartmentSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<DepartmentEntity> departmentEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                departmentEntities = departmentRepo.findByDeptIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_NAME":
                departmentEntities = departmentRepo.findByDeptNameStartingWithIgnoreCaseAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_STATUS":
                departmentEntities = departmentRepo.findByStatusCd(statusCdEnum.getSearchType(), pageable);
                break;
            case "ALL":
            default:
                departmentEntities = departmentRepo.findAll(pageable);
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(departmentEntities)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    @Override
    public List<DepartmentReponse> findAllDepartmentDetails() {

        List<DepartmentEntity> departmentEntities =  departmentRepo.findAllDepartments();
        List<DepartmentReponse> departmentReponses = new ArrayList<>();
        DepartmentReponse departmentReponse = null;
        for(DepartmentEntity departmentEntity : departmentEntities){
            departmentReponse = new DepartmentReponse();

            departmentReponse.setDeptId(departmentEntity.getDeptId());
            departmentReponse.setDeptName(departmentEntity.getDeptName());
            departmentReponse.setStatusCd(departmentEntity.getStatusCd());
            departmentReponses.add(departmentReponse);
        }
        return departmentReponses;
    }

    @Override
    public List<DepartmentReponse> findAllDepartmentDetailsForEmployee() {
        List<DepartmentEntity> departmentEntities =  departmentRepo.findAllDepartmentDetailsForEmployee();
        List<DepartmentReponse> departmentReponses = new ArrayList<>();
        DepartmentReponse departmentReponse = null;
        for(DepartmentEntity departmentEntity : departmentEntities){
            departmentReponse = new DepartmentReponse();
            departmentReponse.setDeptId(departmentEntity.getDeptId());
            departmentReponse.setDeptName(departmentEntity.getDeptName());
            departmentReponse.setStatusCd(departmentEntity.getStatusCd());
            departmentReponses.add(departmentReponse);
        }
        return departmentReponses;
    }

    private DepartmentEntity convertDepartmentCreateRequestToEntity(DepartmentCreateRequest roleCreateRequest) {
        return DepartmentEntity.departmentEntityBuilder()
                .deptName(roleCreateRequest.getDeptName())
                .remark(roleCreateRequest.getRemark())
                .statusCd(roleCreateRequest.getStatusCd())
                .createdUserId(roleCreateRequest.getEmployeeId())
                .build();
    }

    private DepartmentEntity convertDepartmentUpdateRequestToEntity(DepartmentUpdateRequest roleUpdateRequest) {
        return DepartmentEntity.departmentEntityBuilder()
                .deptId(roleUpdateRequest.getDeptId())
                .deptName(roleUpdateRequest.getDeptName())
                .remark(roleUpdateRequest.getRemark())
                .statusCd(roleUpdateRequest.getStatusCd())
                .createdUserId(roleUpdateRequest.getEmployeeId())
                .build();
    }
}
