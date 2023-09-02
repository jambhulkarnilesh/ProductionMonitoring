package com.iot.pmonitor.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "part")
@NoArgsConstructor
@AllArgsConstructor
public class PartEntity extends AuditEnabledEntity {

    @Id
    @Column(name = "PART_ID")
    private Integer partId;

    @Column(name = "PART_NAME")
    private String partName;

    @Column(name = "PART_JOB_TARGET")
    private String partJobTarget;

    @Column(name = "PART_JOB_ASSIGNED")
    private String partJobAssigned;


    @Schema(example = "REMARK")
    private String remark;
    @Column(name = "status")
    private String status;

    @Builder(builderMethodName = "partEntityBuilder")
    public PartEntity(Integer partId, String partName, String partJobTarget, String partJobAssigned, String remark, String status, Instant createdDate, String createdUserId, Instant updatedDate, String updatedUserId) {
        super(createdDate, createdUserId, updatedDate, updatedUserId);
        this.partId = partId;
        this.partName = partName;
        this.partJobTarget = partJobTarget;
        this.partJobAssigned = partJobAssigned;
        this.remark = remark;
        this.status = status;
    }
}
