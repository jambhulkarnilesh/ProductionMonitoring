package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {
    public Page<EmployeeEntity> findByEmpIdAndStatusCd(Integer empId, String statusCd, Pageable pageable);

    public Page<EmployeeEntity> findByEmpFirstNameAndStatusCd(String empFirstName, String statusCd, Pageable pageable);

    public Page<EmployeeEntity> findByDeptIdAndStatusCd(Integer deptId, String statusCd, Pageable pageable);

    public Page<EmployeeEntity> findByDesigIdAndStatusCd(Integer desigId, String statusCd, Pageable pageable);

    public Page<EmployeeEntity> findByRoleIdAndStatusCd(Integer roleId, String statusCd, Pageable pageable);

    public Page<EmployeeEntity> findByEmpMobileNoAndStatusCd(String empMobileNo, String statusCd, Pageable pageable);

    public Page<EmployeeEntity> findByEmpGenderAndStatusCd(String  empGender, String statusCd, Pageable pageable);

    public Page<EmployeeEntity> findByStatusCd(String statusCd, Pageable pageable);

}
