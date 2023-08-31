package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.MachineEntity;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.MachineRepo;
import com.iot.pmonitor.request.MachineRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.MachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineRepo machineRepo;

    @Override
    public PMResponse saveMachine(MachineRequest machineRequest) {
        MachineEntity machineEntity = convertMachineRequestToEntity(machineRequest);
        try {
            machineRepo.save(machineEntity);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside MachineServiceImpl >> saveMachine()");
            throw new PMException("MachineServiceImpl", false, ex.getMessage());
        }
    }

    private MachineEntity convertMachineRequestToEntity(MachineRequest machineRequest) {
        return MachineEntity.machineEntityBuilder()
                .machineName(machineRequest.getMachineName())
                .machineIpAddress(machineRequest.getMachineIpAddress())
                .machinePortNo(machineRequest.getMachinePortNo())
                .machinePLCType(machineRequest.getMachinePLCType())
                .createdUserId(machineRequest.getCreatedUserId())
                .build();
    }
}
