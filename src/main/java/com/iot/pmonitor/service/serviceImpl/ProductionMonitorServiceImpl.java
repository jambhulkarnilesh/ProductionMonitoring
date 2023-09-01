package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.MachineEntity;
import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.entity.ProductionMonitorAudit;
import com.iot.pmonitor.entity.ProductionMonitorEntity;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.MachineRepo;
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


    @Override
    public PMResponse savePMDetails(ProductionMonitorRequest monitorRequest) {

        ProductionMonitorEntity headerEntity = convertProdMonitorRequestToEntity(monitorRequest);
        try {
            productionMonitorRepo.save(headerEntity);
            ProductionMonitorAudit productionHeaderAudit = convertProdMonitorEntityToAudit(headerEntity);
            monitorAuditRepo.save(productionHeaderAudit);
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
        String sortDirection = null;

        Optional<Sort.Order> order = pmSearch.getPageable().getSort().get().findFirst();

        if (order.isPresent()) {
            sortName = order.get().getProperty();  // order by this field
            sortDirection = order.get().getDirection().toString();  //sort ASC or DESC
        }
        List<Object[]> pmData = monitorAuditRepo.getAllProductionMonitor(pmFromDate, pmToDate, pmSearch.getMachineId(), pmSearch.getMachineName(), pmSearch.getMachinePLCType(), pmSearch.getPartId(), pmSearch.getPartName(), pmSearch.getMachTargetJobCount(), pmSearch.getMachCompletedJobCount(), pmSearch.getMachineStatus(), pmSearch.getMachJobStatus(), sortName, pageSize, pageOffset);

        if (!CollectionUtils.isEmpty(pmData)) {
            pmReportResponses = pmData.stream().map(PMReportResponse::new).collect(Collectors.toList());
            System.out.println(pmReportResponses);
        }

        long totalRecords = monitorAuditRepo.getCountAllProductionMonitor(pmFromDate, pmToDate, pmSearch.getMachineId(), pmSearch.getMachineName(), pmSearch.getMachinePLCType(), pmSearch.getPartId(), pmSearch.getPartName(), pmSearch.getMachTargetJobCount(), pmSearch.getMachCompletedJobCount(), pmSearch.getMachineStatus(), pmSearch.getMachJobStatus());

        PageImpl<PMReportResponse> reportResponses = new PageImpl<>(pmReportResponses, pmSearch.getPageable(), totalRecords);
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(reportResponses)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    private ProductionMonitorAudit convertProdMonitorEntityToAudit(ProductionMonitorEntity monintorEntity) {
        return ProductionMonitorAudit.productionMonitorAuditBuilder()

                .machineId(monintorEntity.getMachineId())
                .machineStatus(monintorEntity.getMachineStatus())
                .partId(monintorEntity.getPartId())
                .machTargetJobCount(monintorEntity.getMachTargetJobCount())
                .machCompletedJobCount(monintorEntity.getMachCompletedJobCount())
                .status(monintorEntity.getStatus())
                .isActive(true)
                .createdUserId(monintorEntity.getCreatedUserId())
                .build();
    }

    private ProductionMonitorEntity convertProdMonitorRequestToEntity(ProductionMonitorRequest monitorRequest) {
        return ProductionMonitorEntity.productionMonitorEntityBuilder()

                .machineId(monitorRequest.getMachineId())
                .machineName(getMachineName(monitorRequest.getMachineId()))
                .machineStatus(monitorRequest.getMachineStatus())
                .partId(monitorRequest.getPartId())
                .partName(getPartName(monitorRequest.getPartId()))
                .machTargetJobCount(monitorRequest.getMachTargetJobCount())
                .machCompletedJobCount(monitorRequest.getMachCompletedJobCount())
                .status(monitorRequest.getStatus())
                .isActive(true)
                .createdUserId(monitorRequest.getCreatedUserId())
                .build();
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
