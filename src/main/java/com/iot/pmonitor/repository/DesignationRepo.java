package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.DesignationEntity;
import com.iot.pmonitor.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepo extends JpaRepository<DesignationEntity, Integer> {

    public Page<DesignationEntity> findByDesigIdAndStatusCd(Integer desigId, String statusCd, Pageable pageable);

    public Page<DesignationEntity> findByDeptIdAndStatusCd(Integer deptId, String statusCd, Pageable pageable);

    public Page<DesignationEntity> findByDesigNameAndStatusCd(String desigName, String statusCd, Pageable pageable);

    public Page<DesignationEntity> findByStatusCd(String status, Pageable pageable);
}

