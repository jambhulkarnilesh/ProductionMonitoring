package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.MachineAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineAuditRepo extends JpaRepository<MachineAudit, Integer> {
}
