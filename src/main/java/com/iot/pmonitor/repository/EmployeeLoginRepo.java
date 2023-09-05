package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.EmployeeLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeLoginRepo extends JpaRepository<EmployeeLoginEntity, Integer> {

    public Optional<EmployeeLoginEntity> findByEmpMobileNoAndEmpPasswordAndStatusCd(String empMobileNo, String empPassword, String statusCd);
}
