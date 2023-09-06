package com.iot.pmonitor.service;

import com.iot.pmonitor.enums.PMSearchEnum;
import com.iot.pmonitor.enums.RoleSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.model.PMSearchModel;
import com.iot.pmonitor.request.ProductionMonitorRequest;
import com.iot.pmonitor.response.PMLiveResponse;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductionMonitorService {

    public PMResponse savePMDetails(ProductionMonitorRequest headerRequest);

    public List<PMLiveResponse> getLivePMDetails();

    public PMResponse findPMDetails(PMSearchModel pmSearchModel);
}
