package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.RoleAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAuditRepo extends JpaRepository<RoleAudit, Integer> {
}
