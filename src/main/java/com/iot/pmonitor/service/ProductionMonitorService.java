package com.iot.pmonitor.service;

import com.iot.pmonitor.request.PMSearch;
import com.iot.pmonitor.request.ProductionMonitorRequest;
import com.iot.pmonitor.response.PMLiveResponse;
import com.iot.pmonitor.response.PMResponse;

import java.util.List;

public interface ProductionMonitorService {

    public PMResponse savePMDetails(ProductionMonitorRequest headerRequest);

    public List<PMLiveResponse> getLivePMDetails();

    public PMResponse findPMDetails(PMSearch pmSearchCriteria);
}
