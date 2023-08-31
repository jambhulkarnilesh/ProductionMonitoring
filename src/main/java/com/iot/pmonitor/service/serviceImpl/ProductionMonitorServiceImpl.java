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
import com.iot.pmonitor.request.ProductionMonitorRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.response.ProductionHeaderResponse;
import com.iot.pmonitor.service.ProductionMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductionMonitorServiceImpl implements ProductionMonitorService {
    @Autowired
    private ProductionMonitorRepo productionHeaderRepo;

    @Autowired
    private ProductionMonitorAuditRepo headerAuditRepo;

    @Autowired
    private MachineRepo machineRepo;

    @Autowired
    private PartRepo partRepo;


    @Override
    public PMResponse saveProductionMonitorDetails(ProductionMonitorRequest monitorRequest) {

        ProductionMonitorEntity headerEntity = convertProdMonitorRequestToEntity(monitorRequest);
        try {
            productionHeaderRepo.save(headerEntity);
            ProductionMonitorAudit productionHeaderAudit = convertProdMonitorEntityToAudit(headerEntity);
            headerAuditRepo.save(productionHeaderAudit);
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
    public List<ProductionHeaderResponse> getProductionMonitorDetails() {
        List<ProductionHeaderResponse> productionHeaderResponses = null;
        List<Object[]> productionHeaderData = productionHeaderRepo.getProductionMonitor();
        if (!CollectionUtils.isEmpty(productionHeaderData)) {
            productionHeaderResponses = productionHeaderData.stream()
                    .map(ProductionHeaderResponse::new)
                    .collect(Collectors.toList());
        }
        return productionHeaderResponses;
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

    private String getMachineName(Integer machineId){
        Optional<MachineEntity> machineEntity = machineRepo.findById(machineId);
        if(machineEntity.isPresent()){
            return machineEntity.get().getMachineName();
        }
        return null;
    }

    private String getPartName(Integer partId){
        Optional<PartEntity> partEntity = partRepo.findById(partId);
        if(partEntity.isPresent()){
            return partEntity.get().getPartName();
        }
        return null;
    }
}
