package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.DesignationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DesignationRepo extends JpaRepository<DesignationEntity, Integer> {

    public Page<DesignationEntity> findByDesigIdAndStatusCd(Integer desigId, String statusCd, Pageable pageable);

    public Page<DesignationEntity> findByDeptIdAndStatusCd(Integer deptId, String statusCd, Pageable pageable);

    public Page<DesignationEntity> findByDesigNameStartingWithIgnoreCaseAndStatusCd(String desigName, String statusCd, Pageable pageable);

    public Page<DesignationEntity> findByStatusCd(String status, Pageable pageable);

    @Query(value = "select * from designation desig where desig.status_cd='A' and desig.dept_id = :deptId", nativeQuery = true)
    public List<DesignationEntity> findAllDesignation(Integer deptId);

    public Optional<DesignationEntity> findByDeptIdAndDesigNameEqualsIgnoreCase(Integer deptId, String desigName);
}

