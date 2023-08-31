package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepo extends JpaRepository<MachineEntity, Integer> {
}
