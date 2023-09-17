package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.ReceipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceipesRepo extends JpaRepository<ReceipeEntity, Integer> {

    public Page<ReceipeEntity> findByRecepIdAndStatusCd(Integer recepId, String statusCd, Pageable pageable);

    public Page<ReceipeEntity> findByEmpIdAndStatusCd(Integer empId, String statusCd, Pageable pageable);

    public Page<ReceipeEntity> findByMachineIdAndStatusCd(Integer machineId, String statusCd, Pageable pageable);

    public Page<ReceipeEntity> findByMachTargetJobCountAndStatusCd(Integer machineId, String statusCd, Pageable pageable);
    public Page<ReceipeEntity> findByPartIdAndStatusCd(Integer partId, String statusCd, Pageable pageable);

    public Page<ReceipeEntity> findByRecepStatusStartingWithIgnoreCaseAndStatusCd(String recepStatus, String statusCd, Pageable pageable);

    public Page<ReceipeEntity> findByStatusCd(String status, Pageable pageable);
}
