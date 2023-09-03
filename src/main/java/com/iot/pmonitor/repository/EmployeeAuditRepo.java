package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.EmployeeAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAuditRepo extends JpaRepository<EmployeeAudit, Integer> {
}
