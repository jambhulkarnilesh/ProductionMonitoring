package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.ProductionMonitorAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionMonitorAuditRepo extends JpaRepository<ProductionMonitorAudit, Integer> {
}
