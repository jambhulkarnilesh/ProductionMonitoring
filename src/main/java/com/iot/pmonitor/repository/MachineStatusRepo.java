package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.MachineStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineStatusRepo extends JpaRepository<MachineStatusEntity, Integer> {
}
