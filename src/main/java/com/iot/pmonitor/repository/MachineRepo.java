package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.MachineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepo extends JpaRepository<MachineEntity, Integer> {

    public Page<MachineEntity> findByMachineIdAndStatusCd(Integer machineId, String statusCd, Pageable pageable);

    public Page<MachineEntity> findByMachineNameAndStatusCd(String machineName, String statusCd, Pageable pageable);

    public Page<MachineEntity> findByMachineMaxCapacityAndStatusCd(String machineMaxCapacity, String status,  Pageable pageable);

    public Page<MachineEntity> findByStatusCd(String status,Pageable pageable);
}
