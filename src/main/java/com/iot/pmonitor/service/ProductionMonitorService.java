package com.iot.pmonitor.service;

import com.iot.pmonitor.request.ProductionMonitorRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.response.ProductionHeaderResponse;

import java.util.List;

public interface ProductionMonitorService {

    public PMResponse saveProductionMonitorDetails(ProductionMonitorRequest headerRequest);
    public List<ProductionHeaderResponse> getProductionMonitorDetails();
}
