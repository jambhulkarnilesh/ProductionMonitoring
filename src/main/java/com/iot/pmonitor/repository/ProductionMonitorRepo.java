package com.iot.pmonitor.repository;

import com.iot.pmonitor.constants.SQLQueryConstants;
import com.iot.pmonitor.entity.ProductionMonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionMonitorRepo extends JpaRepository<ProductionMonitorEntity, String> {

    @Query(value = SQLQueryConstants.LIVE_PRODUCTION_MONITOR, nativeQuery = true)
    public List<Object[]> getProductionMonitor();


}
