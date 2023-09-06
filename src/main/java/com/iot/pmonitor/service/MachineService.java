package com.iot.pmonitor.service;

import com.iot.pmonitor.enums.MachineSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.MachineCreateRequest;
import com.iot.pmonitor.request.MachineUpdateRequest;
import com.iot.pmonitor.response.MachineResponse;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MachineService {

    public PMResponse saveMachine(MachineCreateRequest machineRequest);
    public PMResponse updateMachine(MachineUpdateRequest machineUpdateRequest);

    public PMResponse findMachineDetails(MachineSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable pageable, String sortParam, String pageDirection );

    public List<MachineResponse> findAllMachineDetails();
}
