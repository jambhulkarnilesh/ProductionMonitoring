package com.iot.pmonitor.service;

import com.iot.pmonitor.request.MachineStatusRequest;
import com.iot.pmonitor.response.PMResponse;

public interface MachineStatusService {

    public PMResponse saveMachineStatus(MachineStatusRequest machineStatusRequest);
}
