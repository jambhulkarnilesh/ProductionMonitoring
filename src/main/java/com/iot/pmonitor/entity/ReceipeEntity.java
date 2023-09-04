package com.iot.pmonitor.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "recipes")
@NoArgsConstructor
@AllArgsConstructor
public class ReceipeEntity extends AuditEnabledEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recep_id")
    private Integer recepId;

    @Column(name = "recep_date")
    private LocalDateTime recepDate;

    @Column(name = "emp_id")
    private int empId;

    @Column(name = "mach_id")
    private Integer machineId;

    @Column(name = "part_id")
    private Integer partId;

    @Column(name = "mach_target_job_count")
    private String machTargetJobCount;

    @Schema(example = "remark")
    private String remark;

    @Schema(example = "recep_status")
    private String recepStatus;

    @Column(name = "status")
    private String statusCd;

    @Builder(builderMethodName = "receipeEntityBuilder")
    public ReceipeEntity(Integer recepId, LocalDateTime recepDate, int empId, Integer machineId, Integer partId, String machTargetJobCount, String remark, String recepStatus, String statusCd, Instant createdDate, String createdUserId, Instant updatedDate, String updatedUserId) {
        super(createdDate, createdUserId, updatedDate, updatedUserId);
        this.recepId = recepId;
        this.recepDate = recepDate;
        this.empId = empId;
        this.machineId = machineId;
        this.partId = partId;
        this.machTargetJobCount = machTargetJobCount;
        this.remark = remark;
        this.recepStatus = recepStatus;
        this.statusCd = statusCd;
    }
}