package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.DepartmentAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentAuditRepo extends JpaRepository<DepartmentAudit, Integer> {
}
