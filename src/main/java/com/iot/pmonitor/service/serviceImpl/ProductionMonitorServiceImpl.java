package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.MachineEntity;
import com.iot.pmonitor.entity.PartAudit;
import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.entity.ProductionMonitorAudit;
import com.iot.pmonitor.entity.ProductionMonitorEntity;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.MachineRepo;
import com.iot.pmonitor.repository.PartAuditRepo;
import com.iot.pmonitor.repository.PartRepo;
import com.iot.pmonitor.repository.ProductionMonitorAuditRepo;
import com.iot.pmonitor.repository.ProductionMonitorRepo;
import com.iot.pmonitor.request.PMSearch;
import com.iot.pmonitor.request.ProductionMonitorRequest;
import com.iot.pmonitor.response.PMLiveResponse;
import com.iot.pmonitor.response.PMReportResponse;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.ProductionMonitorService;
import com.iot.pmonitor.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductionMonitorServiceImpl implements ProductionMonitorService {
    @Autowired
    private ProductionMonitorRepo productionMonitorRepo;

    @Autowired
    private ProductionMonitorAuditRepo monitorAuditRepo;

    @Autowired
    private MachineRepo machineRepo;

    @Autowired
    private PartRepo partRepo;

    @Autowired
    private PartAuditRepo partAuditRepo;

    @Transactional
    @Override
    public PMResponse savePMDetails(ProductionMonitorRequest monitorRequest) {
        //check part target is set or not. if set then check machine target shouold not be greater than part target
        validateMachineTargetJobCount(monitorRequest.getPartId(), monitorRequest.getMachTargetJobCount());

        ProductionMonitorEntity monitorEntity = convertProdMonitorRequestToEntity(monitorRequest);
        try {
            productionMonitorRepo.save(monitorEntity);
            ProductionMonitorAudit monitorAudit = new ProductionMonitorAudit(monitorEntity);
            monitorAuditRepo.save(monitorAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside ProductionHeaderServiceImpl >> saveProductionHeader()");
            throw new PMException("ProductionHeaderServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public List<PMLiveResponse> getLivePMDetails() {
        List<PMLiveResponse> productionHeaderResponses = null;
        List<Object[]> productionHeaderData = productionMonitorRepo.getProductionMonitor();
        if (!CollectionUtils.isEmpty(productionHeaderData)) {
            productionHeaderResponses = productionHeaderData.stream()
                    .map(PMLiveResponse::new)
                    .collect(Collectors.toList());
        }
        return productionHeaderResponses;
    }

    @Override
    public PMResponse findPMDetails(PMSearch pmSearch) {
        List<PMReportResponse> pmReportResponses = null;
        Integer pageSize = pmSearch.getPageable().getPageSize();
        Integer pageOffset = (int) pmSearch.getPageable().getOffset();

        String pmFromDate = null == pmSearch.getFromDate() ? DateTimeUtils.getFirstDayOfCurrentMonth() : pmSearch.getFromDate();
        String pmToDate = null == pmSearch.getToDate() ? LocalDate.now().toString() : pmSearch.getToDate();

        String sortName = null;
        //String sortDirection = null;

        Optional<Sort.Order> order = pmSearch.getPageable().getSort().get().findFirst();

        if (order.isPresent()) {
            sortName = order.get().getProperty();  // order by this field
            // sortDirection = order.get().getDirection().toString();  //sort ASC or DESC
        }
        List<Object[]> pmData = monitorAuditRepo.getAllProductionMonitor(pmFromDate, pmToDate, pmSearch.getMachineId(), pmSearch.getMachineName(), pmSearch.getMachinePLCType(), pmSearch.getPartId(), pmSearch.getPartName(), pmSearch.getMachTargetJobCount(), pmSearch.getMachCompletedJobCount(), pmSearch.getMachineStatus(), sortName, pageSize, pageOffset);

        if (!CollectionUtils.isEmpty(pmData)) {
            pmReportResponses = pmData.stream().map(PMReportResponse::new).collect(Collectors.toList());
        }

        long totalRecords = monitorAuditRepo.getCountAllProductionMonitor(pmFromDate, pmToDate, pmSearch.getMachineId(), pmSearch.getMachineName(), pmSearch.getMachinePLCType(), pmSearch.getPartId(), pmSearch.getPartName(), pmSearch.getMachTargetJobCount(), pmSearch.getMachCompletedJobCount(), pmSearch.getMachineStatus(), pmSearch.getMachJobStatus());

        PageImpl<PMReportResponse> reportResponses = new PageImpl<>(pmReportResponses, pmSearch.getPageable(), totalRecords);
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(reportResponses)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }


    private ProductionMonitorEntity convertProdMonitorRequestToEntity(ProductionMonitorRequest monitorRequest) {
        return ProductionMonitorEntity.productionMonitorEntityBuilder()

                .machineId(monitorRequest.getMachineId())
                .machineName(getMachineName(monitorRequest.getMachineId()))
                .machineStatus(convertStatusIdToName(monitorRequest.getMachineStatusId()))
                .partId(monitorRequest.getPartId())
                .partName(getPartName(monitorRequest.getPartId()))
                .machTargetJobCount(monitorRequest.getMachTargetJobCount())
                .machCompletedJobCount(monitorRequest.getMachCompletedJobCount())
                .isCompleted(monitorRequest.getIsCompleted())
                .remark(monitorRequest.getRemark())
                .status(monitorRequest.getStatus())
                .createdUserId(monitorRequest.getEmployeeId())
                .build();
    }

    private void validateMachineTargetJobCount(Integer partId, String machTargetJobCount) {
        Optional<PartEntity> partEntity = partRepo.findById(partId);
        Integer balanceAssignedJobCount = 0;
        String partName = null;
        String partJobTarget = null;
        String partJobAssigned = null;
        if (partEntity.isPresent()) {
            partName = partEntity.get().getPartName();
            partJobTarget = partEntity.get().getPartJobTarget();
            partJobAssigned = partEntity.get().getPartJobAssigned();
        }
        if (null != partJobTarget && null != partJobAssigned) {
            if (0 == Integer.parseInt(partJobTarget)) {
                log.error("Inside ProductionMonitorServiceImpl >> validateMachineTargetJobCount()");
                throw new PMException("ProductionMonitorServiceImpl", false, "Please add Target Job Receipe first");
            }
            balanceAssignedJobCount = Integer.parseInt(partJobTarget) - Integer.parseInt(partJobAssigned);
            if (Integer.parseInt(machTargetJobCount) > balanceAssignedJobCount) {
                log.error("Inside ProductionMonitorServiceImpl >> validateMachineTargetJobCount()");
                throw new PMException("ProductionMonitorServiceImpl", false, "Machine Job count is greater than Part target count");
            }
        }
        Integer totalPartJobAssigned = Integer.parseInt(partJobAssigned) + Integer.parseInt(machTargetJobCount);
        partRepo.updateJobPartCount(partId, totalPartJobAssigned);
        PartAudit partAudit = new PartAudit();
        partAudit.setPartId(partId);
        partAudit.setPartName(partName);
        partAudit.setPartJobTarget(partJobTarget);
        partAudit.setPartJobAssigned(totalPartJobAssigned.toString());
        partAudit.setStatus("A");
        //partAudit.setUpdatedDate();
        partAudit.setRemark("Part job assigned for macheine : "+totalPartJobAssigned);
        partAuditRepo.save(partAudit);
    }

    private String convertStatusIdToName(Integer machineStatusId) {
        String statusName = null;
        switch (machineStatusId) {
            case 1:
                statusName = "Running";
                break;
            case 2:
                statusName = "Idle";
                break;
            case 3:
                statusName = "Fault";
                break;
            default:
                statusName = null;
        }
        return statusName;
    }

    private String getMachineName(Integer machineId) {
        Optional<MachineEntity> machineEntity = machineRepo.findById(machineId);
        if (machineEntity.isPresent()) {
            return machineEntity.get().getMachineName();
        }
        return null;
    }

    private String getPartName(Integer partId) {
        Optional<PartEntity> partEntity = partRepo.findById(partId);
        if (partEntity.isPresent()) {
            return partEntity.get().getPartName();
        }
        return null;
    }
}
