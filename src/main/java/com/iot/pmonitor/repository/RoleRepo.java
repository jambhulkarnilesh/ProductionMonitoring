package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {

    public Page<RoleEntity> findByRoleIdAndStatusCd(Integer roleId, String statusCd, Pageable pageable);

    public Page<RoleEntity> findByRoleNameAndStatusCd(String roleName, String statusCd, Pageable pageable);

    public Page<RoleEntity> findByStatusCd(String status, Pageable pageable);
}
