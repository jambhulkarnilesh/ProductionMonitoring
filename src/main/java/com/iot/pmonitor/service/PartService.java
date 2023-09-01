package com.iot.pmonitor.service;

import com.iot.pmonitor.enums.SearchEnum;
import com.iot.pmonitor.request.PartRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;


public interface PartService {

    public PMResponse savePart(PartRequest partRequest);
    public PMResponse findPartDetails(SearchEnum searchEnum, String searchString, Pageable pageable, String sortParam, String pageDirection );
}
