package com.iot.pmonitor.repository;

import com.iot.pmonitor.constants.SQLQueryConstants;
import com.iot.pmonitor.entity.ProductionMonitorAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionMonitorAuditRepo extends JpaRepository<ProductionMonitorAudit, Integer> {

    @Query(value = SQLQueryConstants.PRODUCTION_MONITOR, nativeQuery = true)
    public List<Object[]> getAllProductionMonitor(String fromDate, String toDate, Integer machineId, String machineName, String machinePLCType, Integer partId, String partName, String machTargetJobCount, String machCompletedJobCount, String machineStatus, String sortName, Integer pageSize, Integer pageOffset);

    @Query(value = SQLQueryConstants.COUNT_PRODUCTION_MONITOR, nativeQuery = true)
    public long getCountAllProductionMonitor(String fromDate, String toDate, Integer machineId, String machineName, String machinePLCType, Integer partId, String partName, String machTargetJobCount, String machCompletedJobCount, String machineStatus, String machJobStatus);

}
