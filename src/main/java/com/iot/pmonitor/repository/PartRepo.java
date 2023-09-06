package com.iot.pmonitor.repository;

import com.iot.pmonitor.constants.SQLQueryConstants;
import com.iot.pmonitor.entity.MachineEntity;
import com.iot.pmonitor.entity.PartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepo extends JpaRepository<PartEntity, Integer> {

    public Optional<PartEntity> findByPartNameEqualsIgnoreCase(String partName);
    public Page<PartEntity> findByPartIdAndStatusCd(Integer partId, String statusCd, Pageable pageable);

    public Page<PartEntity> findByPartNameAndStatusCd(String partName, String statusCd, Pageable pageable);

    public Page<PartEntity> findByPartJobTargetAndStatusCd(String partJobTarget, String statusCd, Pageable pageable);

    public Page<PartEntity> findByStatusCd(String status, Pageable pageable);

    @Modifying
    @Query(value = SQLQueryConstants.UPDATE_ASSIGNED_JOB_PART, nativeQuery = true)
    public int updateJobPartCount(Integer partId, Integer totalPartJobAssigned);
}
