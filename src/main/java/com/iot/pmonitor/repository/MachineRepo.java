package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.DepartmentEntity;
import com.iot.pmonitor.entity.MachineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineRepo extends JpaRepository<MachineEntity, Integer> {

    public Optional<MachineEntity> findByMachineNameEqualsIgnoreCase(String machineName);

    public Page<MachineEntity> findByMachineIdStartingWithAndStatusCd(Integer machineId, String statusCd, Pageable pageable);

    public Page<MachineEntity> findByMachineNameStartingWithIgnoreCaseAndStatusCd(String machineName, String statusCd, Pageable pageable);

    public Page<MachineEntity> findByMachineMaxCapacityStartingWithAndStatusCd(String machineMaxCapacity, String status,  Pageable pageable);

    public Page<MachineEntity> findByStatusCd(String status,Pageable pageable);

    @Query(value = "select * from machine where status_cd ='A'", nativeQuery = true)
    public List<MachineEntity> findAllMachines();
}
