package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.EmployeeLoginAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeLoginAuditRepo extends JpaRepository<EmployeeLoginAudit, Integer> {
}
