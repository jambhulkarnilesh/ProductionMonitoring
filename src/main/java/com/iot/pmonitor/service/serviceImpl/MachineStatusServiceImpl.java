package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.MachineStatusEntity;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.MachineStatusRepo;
import com.iot.pmonitor.request.MachineStatusRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.MachineStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MachineStatusServiceImpl implements MachineStatusService {

    @Autowired
    private MachineStatusRepo machineStatusRepo;

    @Override
    public PMResponse saveMachineStatus(MachineStatusRequest machineStatusRequest) {

        MachineStatusEntity machineStatusEntity = convertStatusRequestToEntity(machineStatusRequest);
        try {
            machineStatusRepo.save(machineStatusEntity);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside PartServiceImpl >> savePart()");
            throw new PMException("PartServiceImpl", false, ex.getMessage());
        }
    }

    private MachineStatusEntity convertStatusRequestToEntity(MachineStatusRequest machineStatusRequest) {
        return MachineStatusEntity.machineStatusEntityBuilder()
                .machineStatusName(machineStatusRequest.getMachineStatusName())
                .createdUserId(machineStatusRequest.getCreatedUserId())
                .build();
    }
}
