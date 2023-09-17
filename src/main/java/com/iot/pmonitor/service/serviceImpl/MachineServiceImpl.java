package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.DepartmentEntity;
import com.iot.pmonitor.entity.MachineAudit;
import com.iot.pmonitor.entity.MachineEntity;
import com.iot.pmonitor.enums.MachineSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.MachineAuditRepo;
import com.iot.pmonitor.repository.MachineRepo;
import com.iot.pmonitor.request.MachineCreateRequest;
import com.iot.pmonitor.request.MachineUpdateRequest;
import com.iot.pmonitor.response.MachineResponse;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.MachineService;
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
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineRepo machineRepo;

    @Autowired
    private MachineAuditRepo machineAuditRepo;

    @Override
    public PMResponse saveMachine(MachineCreateRequest machineCreateRequest) {

        Optional<MachineEntity> machineEntities = machineRepo.findByMachineNameEqualsIgnoreCase(machineCreateRequest.getMachineName());
        if(machineEntities.isPresent()){
            log.error("Inside MachineServiceImpl >> saveMachine()");
            throw new PMException("MachineServiceImpl", false, "Machine name already exist");
        }
        MachineEntity machineEntity = convertMachineCreateRequestToEntity(machineCreateRequest);
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
    public PMResponse updateMachine(MachineUpdateRequest machineUpdateRequest) {
        MachineEntity machineEntity = convertMachineUpdateRequestToEntity(machineUpdateRequest);
        try {
            machineRepo.save(machineEntity);
            MachineAudit machineAudit = new MachineAudit(machineEntity);
            machineAuditRepo.save(machineAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_UPDATE)
                    .build();
        } catch (Exception ex) {
            log.error("Inside MachineServiceImpl >> updateMachine()");
            throw new PMException("MachineServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse findMachineDetails(MachineSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<MachineEntity> machineEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                machineEntities = machineRepo.findByMachineIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_NAME":
                machineEntities = machineRepo.findByMachineNameStartingWithIgnoreCaseAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_MACHINE_CAPACITY":
                machineEntities = machineRepo.findByMachineMaxCapacityStartingWithAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_STATUS":
                machineEntities = machineRepo.findByStatusCd(statusCdEnum.getSearchType(), pageable);
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

    @Override
    public List<MachineResponse> findAllMachineDetails() {
        List<MachineEntity> machineEntities =  machineRepo.findAllMachines();
        List<MachineResponse> machineResponses = new ArrayList<>();
        MachineResponse machineResponse = null;
        for(MachineEntity departmentEntity : machineEntities){
            machineResponse = new MachineResponse();

            machineResponse.setMachineId(departmentEntity.getMachineId());
            machineResponse.setMachineName(departmentEntity.getMachineName());
            machineResponse.setStatusCd(departmentEntity.getStatusCd());
            machineResponses.add(machineResponse);
        }
        return machineResponses;
    }

    private MachineEntity convertMachineCreateRequestToEntity(MachineCreateRequest machineCreateRequest) {
        return MachineEntity.machineEntityBuilder()
                .machineName(machineCreateRequest.getMachineName())
                .machineIpAddress(machineCreateRequest.getMachineIpAddress())
                .machinePortNo(machineCreateRequest.getMachinePortNo())
                .machinePLCType(machineCreateRequest.getMachinePLCType())
                .machineMaxCapacity(machineCreateRequest.getMachineMaxCapacity())
                .remark(machineCreateRequest.getRemark())
                .statusCd(machineCreateRequest.getStatusCd())
                .createdUserId(machineCreateRequest.getEmployeeId())
                .build();
    }

    private MachineEntity convertMachineUpdateRequestToEntity(MachineUpdateRequest machineUpdateRequest) {
        return MachineEntity.machineEntityBuilder()
                .machineId(machineUpdateRequest.getMachineId())
                .machineName(machineUpdateRequest.getMachineName())
                .machineIpAddress(machineUpdateRequest.getMachineIpAddress())
                .machinePortNo(machineUpdateRequest.getMachinePortNo())
                .machinePLCType(machineUpdateRequest.getMachinePLCType())
                .machineMaxCapacity(machineUpdateRequest.getMachineMaxCapacity())
                .remark(machineUpdateRequest.getRemark())
                .statusCd(machineUpdateRequest.getStatusCd())
                .createdUserId(machineUpdateRequest.getEmployeeId())
                .build();
    }
}
