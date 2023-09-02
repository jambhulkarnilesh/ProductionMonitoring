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
    private String machJobStatus;

    @Column(name = "MACH_JOB_COMPLETED")
    private String isCompleted;

    @Schema(example = "REMARK")
    private String remark;
    @Column(name = "status")
    private String status;

    @Builder(builderMethodName = "productionMonitorEntityBuilder")
    public ProductionMonitorEntity(Integer machineId, String machineName, String machineStatus, Integer partId, String partName, String machTargetJobCount, String machCompletedJobCount, String machJobStatus, String isCompleted, String remark, String status, Instant createdDate, String createdUserId, Instant updatedDate, String updatedUserId) {
        super(createdDate, createdUserId, updatedDate, updatedUserId);
        this.machineId = machineId;
        this.machineName = machineName;
        this.machineStatus = machineStatus;
        this.partId = partId;
        this.partName = partName;
        this.machTargetJobCount = machTargetJobCount;
        this.machCompletedJobCount = machCompletedJobCount;
        this.machJobStatus = machJobStatus;
        this.isCompleted = isCompleted;
        this.remark = remark;
        this.status = status;
    }
}
