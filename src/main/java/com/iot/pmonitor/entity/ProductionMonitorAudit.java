package com.iot.pmonitor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = "production_monitor_audit")
@NoArgsConstructor
@AllArgsConstructor
public class ProductionMonitorAudit extends AuditEnabledEntity {

    @Id
    @Column(name = "PHA_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prodHeadAuditId;

    @Column(name = "MACH_ID")
    private Integer machineId;

    @Column(name = "MACH_STATUS")
    private String machineStatus;

    @Column(name = "PART_ID")
    private Integer partId;

    @Column(name = "MACH_TARGET_JOB_COUNT")
    private String machTargetJobCount;

    @Column(name = "MACH_COMPLETED_JOB_COUNT")
    private String machCompletedJobCount;

    @Column(name = "MACH_JOB_STATUS")
    private String status;
    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Builder(builderMethodName = "productionMonitorAuditBuilder")
    public ProductionMonitorAudit(Integer machineId, String machineStatus, Integer partId, String machTargetJobCount, String machCompletedJobCount, String status, boolean isActive, String createdUserId, Instant createdDate, Instant updatedDate, String updatedUserId) {
        super(createdDate, createdUserId, updatedDate, updatedUserId);
        this.machineId = machineId;
        this.machineStatus = machineStatus;
        this.partId = partId;
        this.machTargetJobCount = machTargetJobCount;
        this.machCompletedJobCount = machCompletedJobCount;
        this.status = status;
        this.isActive = isActive;
    }
}

