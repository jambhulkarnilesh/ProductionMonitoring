package com.iot.pmonitor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String machJobStatus;

    @Column(name = "MACH_JOB_COMPLETED")
    private String isCompleted;

    @Column(name = "status")
    private String status;

    public ProductionMonitorAudit(ProductionMonitorEntity monitorEntity) {
        super(monitorEntity.getCreatedDate(), monitorEntity.getCreatedUserId(), monitorEntity.getUpdatedDate(), monitorEntity.getUpdatedUserId());
        this.machineId = monitorEntity.getMachineId();
        this.machineStatus = monitorEntity.getMachineStatus();
        this.partId = monitorEntity.getPartId();
        this.machTargetJobCount = monitorEntity.getMachTargetJobCount();
        this.machCompletedJobCount = monitorEntity.getMachCompletedJobCount();
        this.machJobStatus = monitorEntity.getMachJobStatus();
        this.isCompleted = monitorEntity.getIsCompleted();
        this.status = monitorEntity.getStatus();
    }
}

