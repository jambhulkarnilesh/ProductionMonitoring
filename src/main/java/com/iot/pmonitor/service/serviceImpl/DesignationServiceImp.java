package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.DesignationAudit;
import com.iot.pmonitor.entity.DesignationEntity;
import com.iot.pmonitor.enums.DesignationSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.DesignationAuditRepo;
import com.iot.pmonitor.repository.DesignationRepo;
import com.iot.pmonitor.request.DesignationCreateRequest;
import com.iot.pmonitor.request.DesignationUpdateRequest;
import com.iot.pmonitor.response.DesignationReponse;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.DesignationService;
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
public class DesignationServiceImp implements DesignationService {

    @Autowired
    private DesignationRepo designationRepo;

    @Autowired
    private DesignationAuditRepo designationAuditRepo;

    @Override
    public PMResponse saveDesignation(DesignationCreateRequest designationCreateRequest) {
        Optional<DesignationEntity> designationEntities = designationRepo.findByDeptIdAndDesigNameEqualsIgnoreCase(designationCreateRequest.getDeptId(), designationCreateRequest.getDesigName());
        if(designationEntities.isPresent()){
            log.error("Inside DesignationServiceImp >> saveDesignation()");
            throw new PMException("DesignationServiceImp", false, "With Department name Designation name already exist");
        }

        DesignationEntity designationEntity = convertDesignationCreateRequestToEntity(designationCreateRequest);
        try {
            designationRepo.save(designationEntity);
            DesignationAudit designationAudit = new DesignationAudit(designationEntity);
            designationAuditRepo.save(designationAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside DesignationServiceImp >> saveDesignation()");
            throw new PMException("DesignationServiceImp", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse updateDesignation(DesignationUpdateRequest departmentUpdateRequest) {
        DesignationEntity designationEntity = convertDesignationUpdateRequestToEntity(departmentUpdateRequest);
        try {
            designationRepo.save(designationEntity);
            DesignationAudit designationAudit = new DesignationAudit(designationEntity);
            designationAuditRepo.save(designationAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_UPDATE)
                    .build();
        } catch (Exception ex) {
            log.error("Inside DesignationServiceImp >> updateDesignation()");
            throw new PMException("DesignationServiceImp", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse findDesignationDetails(DesignationSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<DesignationEntity> departmentEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                departmentEntities = designationRepo.findByDesigIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_DEPT_ID":
                departmentEntities = designationRepo.findByDeptIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_NAME":
                departmentEntities = designationRepo.findByDesigNameStartingWithIgnoreCaseAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_STATUS":
                departmentEntities = designationRepo.findByStatusCd(statusCdEnum.getSearchType(), pageable);
                break;
            case "ALL":
            default:
                departmentEntities = designationRepo.findAll(pageable);
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(departmentEntities)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    @Override
    public List<DesignationReponse> findAllDesignationDetails(Integer deptId) {

        List<DesignationEntity> designationEntities =  designationRepo.findAllDesignation(deptId);
        List<DesignationReponse> designationReponses = new ArrayList<>();
        DesignationReponse designationReponse = null;
        for(DesignationEntity designationEntity : designationEntities){
            designationReponse = new DesignationReponse();
            designationReponse.setDesigId(designationEntity.getDesigId());
            designationReponse.setDeptId(designationEntity.getDeptId());
            designationReponse.setDesigName(designationEntity.getDesigName());
            designationReponse.setStatusCd(designationEntity.getStatusCd());
            designationReponses.add(designationReponse);
        }
        return designationReponses;
    }

    private DesignationEntity convertDesignationCreateRequestToEntity(DesignationCreateRequest designationCreateRequest) {
        return DesignationEntity.designationEntityBuilder()
                .deptId(designationCreateRequest.getDeptId())
                .desigName(designationCreateRequest.getDesigName())
                .remark(designationCreateRequest.getRemark())
                .statusCd(designationCreateRequest.getStatusCd())
                .createdUserId(designationCreateRequest.getEmployeeId())
                .build();
    }

    private DesignationEntity convertDesignationUpdateRequestToEntity(DesignationUpdateRequest designationUpdateRequest) {
        return DesignationEntity.designationEntityBuilder()
                .desigId(designationUpdateRequest.getDesigId())
                .deptId(designationUpdateRequest.getDeptId())
                .desigName(designationUpdateRequest.getDesigName())
                .remark(designationUpdateRequest.getRemark())
                .statusCd(designationUpdateRequest.getStatusCd())
                .createdUserId(designationUpdateRequest.getEmployeeId())
                .build();
    }
}
