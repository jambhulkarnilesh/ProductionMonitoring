package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.MachineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepo extends JpaRepository<MachineEntity, Integer> {

    public Page<MachineEntity> findByMachineId(Integer machineId, Pageable pageable);

    public Page<MachineEntity> findByMachineName(String machineName, Pageable pageable);

    public Page<MachineEntity> findByStatusCd(String status, Pageable pageable);
}
