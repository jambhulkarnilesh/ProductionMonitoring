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

@Data
@Entity
@Table(name = "machine")
@NoArgsConstructor
@AllArgsConstructor
public class MachineEntity extends AuditEnabledEntity {

    @Id
    @Column(name = "MACH_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer machineId;

    @Column(name = "MACH_NAME")
    private String machineName;

    @Column(name = "MACH_IP_ADDRESS")
    private String machineIpAddress;

    @Column(name = "MACH_PORT_NO")
    private String machinePortNo;

    @Column(name = "MACH_PLC_TYPE")
    private String machinePLCType;

    @Column(name = "MACH_MAX_CAPACITY")
    private String machineMaxCapacity;

    @Schema(example = "REMARK")
    private String remark;
    @Column(name = "status")
    private String status;

    @Builder(builderMethodName = "machineEntityBuilder")
    public MachineEntity(Integer machineId, String machineName, String machineIpAddress, String machinePortNo, String machinePLCType,  String machineMaxCapacity, String remark, String status, String createdUserId, Instant createdDate, Instant updatedDate, String updatedUserId) {
        super(createdDate, createdUserId, updatedDate, updatedUserId);
        this.machineName = machineName;
        this.machineIpAddress = machineIpAddress;
        this.machinePortNo = machinePortNo;
        this.machinePLCType = machinePLCType;
        this.machineMaxCapacity = machineMaxCapacity;
        this.remark = remark;
        this.status = status;
    }
}
