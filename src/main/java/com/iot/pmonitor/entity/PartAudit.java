package com.iot.pmonitor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "part_audit")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PartAudit extends AuditEnabledEntity {
    @Id
    @Column(name = "PART_AUD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partAuditId;

    @Column(name = "PART_ID")
    private Integer partId;
    @Column(name = "PART_NAME")
    private String partName;

    @Column(name = "PART_JOB_TARGET")
    private String partJobTarget;

    @Column(name = "PART_JOB_ASSIGNED")
    private String partJobAssigned;
    @Column(name = "status")
    private String status;

    public PartAudit(PartEntity partEntity) {
        super(partEntity.getCreatedDate(), partEntity.getCreatedUserId(), partEntity.getUpdatedDate(), partEntity.getUpdatedUserId());
        this.partId = partEntity.getPartId();
        this.partName = partEntity.getPartName();
        this.partJobTarget = partEntity.getPartJobTarget();
        this.partJobAssigned = partEntity.getPartJobAssigned();
        this.partJobAssigned = partEntity.getPartJobAssigned();
        this.status = partEntity.getStatus();
    }
}
