package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.PartAudit;
import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.entity.ReceipeAudit;
import com.iot.pmonitor.entity.ReceipeEntity;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
    public PMResponse findReceipesDetails(ReceipesSearchModel receipesSearchModel) {
        List<RecipesResponse> pmReportResponses = null;
      /*  Integer pageSize = receipesSearchModel.getPageable().getPageSize();
        Integer pageOffset = (int) receipesSearchModel.getPageable().getOffset();

        String recepFromDate = null == receipesSearchModel.getFromDate() ? DateTimeUtils.getFirstDayOfCurrentMonth() : receipesSearchModel.getFromDate();
        String recepToDate = null == receipesSearchModel.getToDate() ? LocalDate.now().toString() : receipesSearchModel.getToDate();

        String sortName = null;
        //String sortDirection = null;

        Optional<Sort.Order> order = receipesSearchModel.getPageable().getSort().get().findFirst();

        if (order.isPresent()) {
            sortName = order.get().getProperty();  // order by this field
            // sortDirection = order.get().getDirection().toString();  //sort ASC or DESC
        }
        List<Object[]> pmData = receipesAuditRepo.getAllProductionMonitor(recepFromDate, recepToDate, receipesSearchModel.getMachineId(), receipesSearchModel.getMachineName(), pmSearchModel.getMachinePLCType(), pmSearchModel.getPartId(), pmSearchModel.getPartName(), pmSearchModel.getMachTargetJobCount(), pmSearchModel.getMachCompletedJobCount(), pmSearchModel.getMachineStatus(), sortName, pageSize, pageOffset);

        if (!CollectionUtils.isEmpty(pmData)) {
            pmReportResponses = pmData.stream().map(PMReportResponse::new).collect(Collectors.toList());
        }

        long totalRecords = monitorAuditRepo.getCountAllProductionMonitor(recepFromDate, recepToDate, receipesSearchModel.getMachineId(), pmSearchModel.getMachineName(), pmSearchModel.getMachinePLCType(), pmSearchModel.getPartId(), pmSearchModel.getPartName(), pmSearchModel.getMachTargetJobCount(), pmSearchModel.getMachCompletedJobCount(), pmSearchModel.getMachineStatus(), pmSearchModel.getMachJobStatus());

        PageImpl<PMReportResponse> reportResponses = new PageImpl<>(pmReportResponses, receipesSearchModel.getPageable(), totalRecords);
        */return PMResponse.builder()
                .isSuccess(true)
                .responseData(null)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    private ReceipeEntity convertRecipesCreateRequestToEntity(RecipesCreateRequest recipesCreateRequest) {
        return ReceipeEntity.receipeEntityBuilder()
               // .recepDate(recipesCreateRequest.getRecepDate())
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
               // .recepDate(recipesUpdateRequest.getRecepDate())
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
