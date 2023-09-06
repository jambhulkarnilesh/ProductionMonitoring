package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.DesignationAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationAuditRepo extends JpaRepository<DesignationAudit, Integer> {
}
