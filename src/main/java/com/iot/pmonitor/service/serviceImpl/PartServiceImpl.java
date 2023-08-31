package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.PartRepo;
import com.iot.pmonitor.request.PartRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.PartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepo partRepo;

    @Override
    public PMResponse savePart(PartRequest partRequest) {
        PartEntity partEntity = convertPartRequestToEntity(partRequest);
        try {
            partRepo.save(partEntity);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside PartServiceImpl >> savePart()");
            throw new PMException("PartServiceImpl", false, ex.getMessage());
        }
    }

    private PartEntity convertPartRequestToEntity(PartRequest partRequest) {
        return PartEntity.partEntityBuilder()
                .partName(partRequest.getPartName())
                .createdUserId(partRequest.getCreatedUserId())
                .build();
    }
}
