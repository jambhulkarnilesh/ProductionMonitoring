package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {
    public Page<EmployeeEntity> findByEmpId(Integer empId, Pageable pageable);

    public Page<EmployeeEntity> findByEmpFirstName(String empId, Pageable pageable);

    public Page<EmployeeEntity> findByStatusCd(String empId, Pageable pageable);

}
