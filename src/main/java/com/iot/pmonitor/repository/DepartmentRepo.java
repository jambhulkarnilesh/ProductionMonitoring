package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.DepartmentEntity;
import com.iot.pmonitor.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentEntity, Integer> {

    public Page<DepartmentEntity> findByDeptIdAndStatusCd(Integer deptId, String statusCd, Pageable pageable);

    public Page<DepartmentEntity> findByDeptNameAndStatusCd(String deptName, String statusCd, Pageable pageable);

    public Page<DepartmentEntity> findByStatusCd(String status, Pageable pageable);
}
