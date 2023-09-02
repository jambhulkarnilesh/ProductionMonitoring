package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.PartAudit;
import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.enums.SearchEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.PartAuditRepo;
import com.iot.pmonitor.repository.PartRepo;
import com.iot.pmonitor.request.PartRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.PartService;
import com.iot.pmonitor.utils.PMUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepo partRepo;

    @Autowired
    private PartAuditRepo partAuditRepo;

    @Override
    public PMResponse savePart(PartRequest partRequest) {
        PartEntity partEntity = convertPartRequestToEntity(partRequest);
        try {
            partRepo.save(partEntity);
            PartAudit partAudit = new PartAudit(partEntity);
            partAuditRepo.save(partAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside PartServiceImpl >> savePart()");
            throw new PMException("PartServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse findPartDetails(SearchEnum searchEnum, String searchString, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<PartEntity> partEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                partEntities = partRepo.findByPartId(Integer.parseInt(searchString), pageable);
                break;
            case "BY_NAME":
                partEntities = partRepo.findByPartName(searchString, pageable);
                break;
            default:
                partEntities = partRepo.findAll(pageable);
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(partEntities)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    private PartEntity convertPartRequestToEntity(PartRequest partRequest) {
        return PartEntity.partEntityBuilder()
                .partId(partRequest.getPartId())
                .partName(partRequest.getPartName())
                .partJobTarget(partRequest.getPartJobTarget())
                .partJobAssigned(partRequest.getPartJobAssigned())
                .status("A")
                .createdUserId(partRequest.getCreatedUserId())
                .build();
    }
}
