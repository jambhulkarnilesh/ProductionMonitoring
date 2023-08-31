package com.iot.pmonitor.service;

import com.iot.pmonitor.request.PartRequest;
import com.iot.pmonitor.response.PMResponse;

public interface PartService {

    public PMResponse savePart(PartRequest partRequest);
}
