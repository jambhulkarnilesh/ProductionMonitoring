package com.iot.pmonitor.repository;

import com.iot.pmonitor.constants.SQLQueryConstants;
import com.iot.pmonitor.entity.PartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepo extends JpaRepository<PartEntity, Integer> {

    public Page<PartEntity> findByPartId(Integer partId, Pageable pageable);

    public Page<PartEntity> findByPartName(String partName, Pageable pageable);

    @Modifying
    @Query(value = SQLQueryConstants.UPDATE_ASSIGNED_JOB_PART, nativeQuery = true)
    public int updateJobPartCount(Integer partId, Integer totalPartJobAssigned);
}
