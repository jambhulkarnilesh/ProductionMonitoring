package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.MachineAudit;
import com.iot.pmonitor.entity.MachineEntity;
import com.iot.pmonitor.enums.SearchEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.MachineAuditRepo;
import com.iot.pmonitor.repository.MachineRepo;
import com.iot.pmonitor.request.MachineRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.MachineService;
import com.iot.pmonitor.utils.PMUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineRepo machineRepo;

    @Autowired
    private MachineAuditRepo machineAuditRepo;

    @Override
    public PMResponse saveMachine(MachineRequest machineRequest) {
        MachineEntity machineEntity = convertMachineRequestToEntity(machineRequest);
        try {
            machineRepo.save(machineEntity);
            MachineAudit machineAudit = new MachineAudit(machineEntity);
            machineAuditRepo.save(machineAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside MachineServiceImpl >> saveMachine()");
            throw new PMException("MachineServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse findMachineDetails(SearchEnum searchEnum, String searchString, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<MachineEntity> machineEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                machineEntities = machineRepo.findByMachineId(Integer.parseInt(searchString), pageable);
                break;
            case "BY_NAME":
                machineEntities = machineRepo.findByMachineName(searchString, pageable);
                break;
            default:
                machineEntities = machineRepo.findAll(pageable);
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(machineEntities)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    private MachineEntity convertMachineRequestToEntity(MachineRequest machineRequest) {
        return MachineEntity.machineEntityBuilder()
                .machineName(machineRequest.getMachineName())
                .machineIpAddress(machineRequest.getMachineIpAddress())
                .machinePortNo(machineRequest.getMachinePortNo())
                .machinePLCType(machineRequest.getMachinePLCType())
                .machineMaxCapacity(machineRequest.getMachineMaxCapacity())
                .status("A")
                .createdUserId(machineRequest.getCreatedUserId())
                .build();
    }
}
