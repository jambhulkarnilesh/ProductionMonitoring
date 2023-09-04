package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.ReceipeAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceipesAuditRepo extends JpaRepository<ReceipeAudit, Integer> {
}
