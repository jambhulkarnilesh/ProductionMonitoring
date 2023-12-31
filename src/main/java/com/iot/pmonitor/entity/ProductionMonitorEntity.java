package com.iot.pmonitor.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = "production_monitor")
@NoArgsConstructor
@AllArgsConstructor
public class ProductionMonitorEntity extends AuditEnabledEntity {

    @Id
    @Column(name = "MACH_ID")
    private Integer machineId;

    @Column(name = "MACH_NAME")
    private String machineName;

    @Column(name = "MACH_STATUS")
    private String machineStatus;

    @Column(name = "PART_ID")
    private Integer partId;

    @Column(name = "PART_NAME")
    private String partName;
    @Column(name = "MACH_TARGET_JOB_COUNT")
    private String machTargetJobCount;

    @Column(name = "MACH_COMPLETED_JOB_COUNT")
    private String machCompletedJobCount;

    @Column(name = "MACH_JOB_STATUS")
    private String status;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Builder(builderMethodName = "productionMonitorEntityBuilder")
    public ProductionMonitorEntity(Integer machineId,  String machineName, String machineStatus, Integer partId, String partName, String machTargetJobCount, String machCompletedJobCount, String status, boolean isActive, Instant createdDate, String createdUserId, Instant updatedDate, String updatedUserId) {
        super(createdDate, createdUserId, updatedDate, updatedUserId);
        this.machineId = machineId;
        this.machineName = machineName;
        this.machineStatus = machineStatus;
        this.partId = partId;
        this.partName = partName;
        this.machTargetJobCount = machTargetJobCount;
        this.machCompletedJobCount = machCompletedJobCount;
        this.status = status;
        this.isActive = isActive;
    }
}
