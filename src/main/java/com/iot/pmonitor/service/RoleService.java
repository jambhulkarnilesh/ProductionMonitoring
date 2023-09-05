package com.iot.pmonitor.service;

import com.iot.pmonitor.entity.DepartmentEntity;
import com.iot.pmonitor.entity.RoleEntity;
import com.iot.pmonitor.enums.PartSearchEnum;
import com.iot.pmonitor.enums.RoleSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.request.PartCreateRequest;
import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.request.RoleCreateRequest;
import com.iot.pmonitor.request.RoleUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    public PMResponse saveRole(RoleCreateRequest roleCreateRequest);

    public PMResponse updateRole(RoleUpdateRequest roleUpdateRequest);

    public PMResponse findRoleDetails(RoleSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable pageable, String sortParam, String pageDirection );

    public List<RoleEntity> findAllRolesDetails();
}
