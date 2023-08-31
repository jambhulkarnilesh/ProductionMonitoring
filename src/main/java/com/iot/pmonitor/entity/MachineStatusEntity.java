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
@Table(name = "machine_status")
@NoArgsConstructor
@AllArgsConstructor
public class MachineStatusEntity extends AuditEnabledEntity {

    @Id
    @Column(name = "MACH_STATUS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer machineStatusId;

    @Column(name = "MACH_STATUS_NAME")
    private String machineStatusName;

    @Builder(builderMethodName = "machineStatusEntityBuilder")
    public MachineStatusEntity(String machineStatusName, String createdUserId, Instant createdDate, Instant updatedDate, String updatedUserId) {
        super(createdDate, createdUserId, updatedDate, updatedUserId);
        this.machineStatusName = machineStatusName;
    }
}
