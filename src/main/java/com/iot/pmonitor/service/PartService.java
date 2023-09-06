package com.iot.pmonitor.service;

import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.entity.RoleEntity;
import com.iot.pmonitor.enums.PartSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.PartCreateRequest;
import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PartService {

    public PMResponse savePart(PartCreateRequest partRequest);

    public PMResponse updatePart(PartUpdateRequest partUpdateRequest);
    public PMResponse findPartDetails(PartSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable pageable, String sortParam, String pageDirection );

    public List<PartEntity> findAllPartDetails();
}
