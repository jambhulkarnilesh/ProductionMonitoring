package com.iot.pmonitor.repository;

import com.iot.pmonitor.entity.ReceipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceipesRepo extends JpaRepository<ReceipeEntity, Integer> {
}
