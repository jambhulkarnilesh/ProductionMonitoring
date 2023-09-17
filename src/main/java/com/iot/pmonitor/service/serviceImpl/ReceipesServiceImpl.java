package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.PartAudit;
import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.entity.ReceipeAudit;
import com.iot.pmonitor.entity.ReceipeEntity;
import com.iot.pmonitor.enums.PartSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.model.ReceipesSearchModel;
import com.iot.pmonitor.repository.ReceipesAuditRepo;
import com.iot.pmonitor.repository.ReceipesRepo;
import com.iot.pmonitor.request.PartCreateRequest;
import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.request.RecipesCreateRequest;
import com.iot.pmonitor.request.RecipesUpdateRequest;
import com.iot.pmonitor.response.PMReportResponse;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.response.RecipesResponse;
import com.iot.pmonitor.service.RecipesService;
import com.iot.pmonitor.utils.DateTimeUtils;
import com.iot.pmonitor.utils.PMUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReceipesServiceImpl implements RecipesService {

    @Autowired
    private ReceipesRepo receipesRepo;

    @Autowired
    private ReceipesAuditRepo receipesAuditRepo;

    @Override
    public PMResponse saveRecipes(RecipesCreateRequest recipesCreateRequest) {
        ReceipeEntity receipeEntity = convertRecipesCreateRequestToEntity(recipesCreateRequest);
        try {
            receipesRepo.save(receipeEntity);
            ReceipeAudit partAudit = new ReceipeAudit(receipeEntity);
            receipesAuditRepo.save(partAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside ReceipesServiceImpl >> savePart()");
            throw new PMException("ReceipesServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse updateRecipes(RecipesUpdateRequest recipesUpdateRequest) {
        ReceipeEntity receipeEntity = convertRecipesUpdateRequestToEntity(recipesUpdateRequest);
        try {
            receipesRepo.save(receipeEntity);
            ReceipeAudit receipeAudit = new ReceipeAudit(receipeEntity);
            receipesAuditRepo.save(receipeAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_UPDATE)
                    .build();
        } catch (Exception ex) {
            log.error("Inside ReceipesServiceImpl >> updateRecipes()");
            throw new PMException("ReceipesServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse findRecipesDetails(PartSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<ReceipeEntity> receipeEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                receipeEntities = receipesRepo.findByRecepIdStartingWithAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_EMP_ID":
                receipeEntities = receipesRepo.findByEmpIdStartingWithAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_MACHINE_ID":
                receipeEntities = receipesRepo.findByMachineIdStartingWithAndStatusCd(searchString, statusCdEnum.getSearchType(),pageable);
                break;
            case "BY_PART_ID":
                receipeEntities = receipesRepo.findByPartIdStartingWithAndStatusCd(searchString, statusCdEnum.getSearchType(),pageable);
                break;
            case "BY_JOB_TARGET":
                receipeEntities = receipesRepo.findByMachTargetJobCountStartingWithAndStatusCd(searchString, statusCdEnum.getSearchType(),pageable);
                break;
            case "BY_RECEIPE_STATUS":
                receipeEntities = receipesRepo.findByRecepStatusStartingWithIgnoreCaseAndStatusCd(searchString, statusCdEnum.getSearchType(),pageable);
                break;
            case "BY_STATUS":
                receipeEntities = receipesRepo.findByStatusCd(statusCdEnum.getSearchType(), pageable);
                break;
            case "ALL":
            default:
                receipeEntities = receipesRepo.findAll(pageable);
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(receipeEntities)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }



    private ReceipeEntity convertRecipesCreateRequestToEntity(RecipesCreateRequest recipesCreateRequest) {
        return ReceipeEntity.receipeEntityBuilder()
                .recepDate(recipesCreateRequest.getRecepDate())
                .empId(recipesCreateRequest.getEmpId())
                .machineId(recipesCreateRequest.getMachineId())
                .partId(recipesCreateRequest.getPartId())
                .machTargetJobCount(recipesCreateRequest.getMachTargetJobCount())
                .remark(recipesCreateRequest.getRemark())
                .recepStatus(recipesCreateRequest.getRecepStatus())
                .statusCd(recipesCreateRequest.getStatusCd())
                .createdUserId(recipesCreateRequest.getEmployeeId())
                .build();
    }

    private ReceipeEntity convertRecipesUpdateRequestToEntity(RecipesUpdateRequest recipesUpdateRequest) {
        return ReceipeEntity.receipeEntityBuilder()
                .recepId(recipesUpdateRequest.getRecepId())
                .recepDate(recipesUpdateRequest.getRecepDate())
                .empId(recipesUpdateRequest.getEmpId())
                .machineId(recipesUpdateRequest.getMachineId())
                .partId(recipesUpdateRequest.getPartId())
                .machTargetJobCount(recipesUpdateRequest.getMachTargetJobCount())
                .remark(recipesUpdateRequest.getRemark())
                .recepStatus(recipesUpdateRequest.getRecepStatus())
                .statusCd(recipesUpdateRequest.getStatusCd())
                .createdUserId(recipesUpdateRequest.getEmployeeId())
                .build();
    }
}
