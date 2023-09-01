package com.iot.pmonitor.service;

import com.iot.pmonitor.enums.SearchEnum;
import com.iot.pmonitor.request.MachineRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

public interface MachineService {

    public PMResponse saveMachine(MachineRequest machineRequest);

    public PMResponse findMachineDetails(SearchEnum searchEnum, String searchString, Pageable pageable, String sortParam, String pageDirection );
}
