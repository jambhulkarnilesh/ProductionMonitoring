package com.iot.pmonitor.repository;

import com.iot.pmonitor.constants.SQLQueryConstants;
import com.iot.pmonitor.entity.ProductionMonitorAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionMonitorAuditRepo extends JpaRepository<ProductionMonitorAudit, Integer> {

    //for Machine Id
    @Query(value = SQLQueryConstants.PM_BY_MONITOR_ID, nativeQuery = true)
    public List<Object[]> getAllPMByMonitorId(String fromDate, String toDate, Integer machineId, String sortName, Integer pageSize, Integer pageOffset);

    @Query(value = SQLQueryConstants.COUNT_BY_MONITOR_ID, nativeQuery = true)
    public long getCountPMByMonitorId(String fromDate, String toDate, Integer machineId);

    //for Part Id
    @Query(value = SQLQueryConstants.PM_BY_PART_ID, nativeQuery = true)
    public List<Object[]> getAllPMByPartId(String fromDate, String toDate, Integer partId, String sortName, Integer pageSize, Integer pageOffset);

    @Query(value = SQLQueryConstants.COUNT_BY_PART_ID, nativeQuery = true)
    public long getCountPMByPartId(String fromDate, String toDate, Integer partId);

    // for machine target job count
    @Query(value = SQLQueryConstants.PM_BY_JOB_TARGET_COUNT, nativeQuery = true)
    public List<Object[]> getAllPMByJobTargetCount(String fromDate, String toDate, String machTargetJobCount, String sortName, Integer pageSize, Integer pageOffset);

    @Query(value = SQLQueryConstants.COUNT_BY_JOB_TARGET_COUNT, nativeQuery = true)
    public long getCountByJobTargetCount(String fromDate, String toDate, String machTargetJobCount);

    // for machine job status
    @Query(value = SQLQueryConstants.PM_BY_MACH_JOB_STATUS, nativeQuery = true)
    public List<Object[]> getAllPMByMachineJobStatus(String fromDate, String toDate, String machJobStatus, String sortName, Integer pageSize, Integer pageOffset);

    @Query(value = SQLQueryConstants.COUNT_BY_MACH_JOB_STATUS, nativeQuery = true)
    public long getCountByMachineJobStatus(String fromDate, String toDate, String machJobStatus);

    // for all
    @Query(value = SQLQueryConstants.PM_BY_ALL, nativeQuery = true)
    public List<Object[]> getAllPMByAll(String fromDate, String toDate, String sortName, Integer pageSize, Integer pageOffset);

    @Query(value = SQLQueryConstants.COUNT_BY_ALL, nativeQuery = true)
    public long getCountByAll(String fromDate, String toDate);

}
