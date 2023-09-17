package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.DepartmentEntity;
import com.iot.pmonitor.entity.PartAudit;
import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.enums.PartSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.PartAuditRepo;
import com.iot.pmonitor.repository.PartRepo;
import com.iot.pmonitor.request.PartCreateRequest;
import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.PartService;
import com.iot.pmonitor.utils.PMUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepo partRepo;

    @Autowired
    private PartAuditRepo partAuditRepo;

    @Override
    public PMResponse savePart(PartCreateRequest partCreateRequest) {

        Optional<PartEntity> optionalPartEntity = partRepo.findByPartNameEqualsIgnoreCase(partCreateRequest.getPartName());
        if(optionalPartEntity.isPresent()){
            log.error("Inside PartServiceImpl >> savePart()");
            throw new PMException("PartServiceImpl", false, "Part name already exist");
        }

        PartEntity partEntity = convertPartCreateRequestToEntity(partCreateRequest);
        try {
            partRepo.save(partEntity);
            PartAudit partAudit = new PartAudit(partEntity);
            partAuditRepo.save(partAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside PartServiceImpl >> savePart()");
            throw new PMException("PartServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse updatePart(PartUpdateRequest partUpdateRequest) {
        PartEntity partEntity = convertPartUpdateRequestToEntity(partUpdateRequest);
        try {
            partRepo.save(partEntity);
            PartAudit partAudit = new PartAudit(partEntity);
            partAuditRepo.save(partAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_UPDATE)
                    .build();
        } catch (Exception ex) {
            log.error("Inside PartServiceImpl >> updatePart()");
            throw new PMException("PartServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse findPartDetails(PartSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<PartEntity> partEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                partEntities = partRepo.findByPartIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_NAME":
                partEntities = partRepo.findByPartNameStartingWithIgnoreCaseAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_JOB_TARGET":
                partEntities = partRepo.findByPartJobTargetAndStatusCd(searchString, statusCdEnum.getSearchType(),pageable);
                break;
            case "BY_STATUS":
                partEntities = partRepo.findByStatusCd(statusCdEnum.getSearchType(), pageable);
                break;
            case "ALL":
            default:
                partEntities = partRepo.findAll(pageable);
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(partEntities)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    @Override
    public List<PartEntity> findAllPartDetails() {
        return partRepo.findAll();
    }

    private PartEntity convertPartCreateRequestToEntity(PartCreateRequest partCreateRequest) {
        return PartEntity.partEntityBuilder()
                .partName(partCreateRequest.getPartName())
                .partJobTarget(partCreateRequest.getPartJobTarget())
                .partJobAssigned(partCreateRequest.getPartJobAssigned())
                .remark(partCreateRequest.getRemark())
                .statusCd(partCreateRequest.getStatusCd())
                .createdUserId(partCreateRequest.getEmployeeId())
                .build();
    }

    private PartEntity convertPartUpdateRequestToEntity(PartUpdateRequest partUpdateRequest) {
        return PartEntity.partEntityBuilder()
                .partId(partUpdateRequest.getPartId())
                .partName(partUpdateRequest.getPartName())
                .partJobTarget(partUpdateRequest.getPartJobTarget())
                .partJobAssigned(partUpdateRequest.getPartJobAssigned())
                .remark(partUpdateRequest.getRemark())
                .statusCd(partUpdateRequest.getStatusCd())
                .createdUserId(partUpdateRequest.getEmployeeId())
                .build();
    }
}
