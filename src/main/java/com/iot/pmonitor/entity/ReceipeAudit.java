package com.iot.pmonitor.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "recipes_audit")
@NoArgsConstructor
@AllArgsConstructor
public class ReceipeAudit extends  AuditEnabledEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recep_aud_id")
    private Integer recepAuditId;

    @Column(name = "recep_id")
    private Integer recepId;

    @Column(name = "recep_date")
    private Timestamp recepDate;

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

    @Column(name = "status_cd")
    private String statusCd;

    public ReceipeAudit(ReceipeEntity receipeEntity){
        super(receipeEntity.getCreatedDate(), receipeEntity.getCreatedUserId(), receipeEntity.getUpdatedDate(), receipeEntity.getUpdatedUserId());
        this.recepId = receipeEntity.getRecepId();
        this.recepDate = receipeEntity.getRecepDate();
        this.empId = receipeEntity.getEmpId();
        this.machineId = receipeEntity.getMachineId();
        this.partId = receipeEntity.getPartId();
        this.machTargetJobCount = receipeEntity.getMachTargetJobCount();
        this.remark = receipeEntity.getRemark();
        this.recepStatus = receipeEntity.getRecepStatus();
        this.statusCd = receipeEntity.getStatusCd();
    }
}
