package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepo extends JpaRepository<PartEntity, Integer> {
}
