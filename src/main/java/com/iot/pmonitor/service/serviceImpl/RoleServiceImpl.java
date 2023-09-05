package com.iot.pmonitor.service.serviceImpl;

import com.iot.pmonitor.constants.PMConstants;
import com.iot.pmonitor.entity.PartEntity;
import com.iot.pmonitor.entity.RoleAudit;
import com.iot.pmonitor.entity.RoleEntity;
import com.iot.pmonitor.enums.PartSearchEnum;
import com.iot.pmonitor.enums.RoleSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.exception.PMException;
import com.iot.pmonitor.repository.RoleAuditRepo;
import com.iot.pmonitor.repository.RoleRepo;
import com.iot.pmonitor.request.RoleCreateRequest;
import com.iot.pmonitor.request.RoleUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import com.iot.pmonitor.service.RoleService;
import com.iot.pmonitor.utils.PMUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private RoleAuditRepo roleAuditRepo;

    @Override
    public PMResponse saveRole(RoleCreateRequest roleCreateRequest) {
        RoleEntity partEntity = convertRoleCreateRequestToEntity(roleCreateRequest);
        try {
            roleRepo.save(partEntity);
            RoleAudit partAudit = new RoleAudit(partEntity);
            roleAuditRepo.save(partAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_SUCCESS)
                    .build();
        } catch (Exception ex) {
            log.error("Inside RoleServiceImpl >> saveRole()");
            throw new PMException("RoleServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse updateRole(RoleUpdateRequest roleUpdateRequest) {
        RoleEntity roleEntity = convertRoleUpdateRequestToEntity(roleUpdateRequest);
        try {
            roleRepo.save(roleEntity);
            RoleAudit partAudit = new RoleAudit(roleEntity);
            roleAuditRepo.save(partAudit);
            return PMResponse.builder()
                    .isSuccess(true)
                    .responseMessage(PMConstants.RECORD_UPDATE)
                    .build();
        } catch (Exception ex) {
            log.error("Inside RoleServiceImpl >> updateRole()");
            throw new PMException("RoleServiceImpl", false, ex.getMessage());
        }
    }

    @Override
    public PMResponse findRoleDetails(RoleSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable requestPageable, String sortParam, String pageDirection) {
        Page<RoleEntity> roleEntities = null;
        Pageable pageable = PMUtils.sort(requestPageable, sortParam, pageDirection);
        switch (searchEnum.getSearchType()) {
            case "BY_ID":
                roleEntities = roleRepo.findByRoleIdAndStatusCd(Integer.parseInt(searchString), statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_NAME":
                roleEntities = roleRepo.findByRoleNameAndStatusCd(searchString, statusCdEnum.getSearchType(), pageable);
                break;
            case "BY_STATUS":
                roleEntities = roleRepo.findByStatusCd(statusCdEnum.getSearchType(), pageable);
                break;
            case "ALL":
            default:
                roleEntities = roleRepo.findAll(pageable);
        }
        return PMResponse.builder()
                .isSuccess(true)
                .responseData(roleEntities)
                .responseMessage(PMConstants.RECORD_FETCH)
                .build();
    }

    @Override
    public List<RoleEntity> findAllRolesDetails() {
        return roleRepo.findAll();
    }

    private RoleEntity convertRoleCreateRequestToEntity(RoleCreateRequest roleCreateRequest) {
        return RoleEntity.roleEntityBuilder()
                .roleName(roleCreateRequest.getRoleName())
                .remark(roleCreateRequest.getRemark())
                .statusCd(roleCreateRequest.getStatusCd())
                .createdUserId(roleCreateRequest.getEmployeeId())
                .build();
    }

    private RoleEntity convertRoleUpdateRequestToEntity(RoleUpdateRequest roleUpdateRequest) {
        return RoleEntity.roleEntityBuilder()
                .roleId(roleUpdateRequest.getRoleId())
                .roleName(roleUpdateRequest.getRoleName())
                .remark(roleUpdateRequest.getRemark())
                .statusCd(roleUpdateRequest.getStatusCd())
                .createdUserId(roleUpdateRequest.getEmployeeId())
                .build();
    }
}
