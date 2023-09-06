package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.PartAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartAuditRepo extends JpaRepository<PartAudit, Integer> {
}
