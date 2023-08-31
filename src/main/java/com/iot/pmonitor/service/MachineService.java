package com.iot.pmonitor.service;

import com.iot.pmonitor.request.MachineRequest;
import com.iot.pmonitor.response.PMResponse;

public interface MachineService {

    public PMResponse saveMachine(MachineRequest machineRequest);
}
