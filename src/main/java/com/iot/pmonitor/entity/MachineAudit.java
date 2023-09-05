package com.iot.pmonitor.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "machine_audit")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MachineAudit extends AuditEnabledEntity {

    @Id
    @Column(name = "MACH_AUD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer machineAuditId;

    @Column(name = "MACH_ID")
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
    @Column(name = "status_cd")
    private String statusCd;

    public MachineAudit(MachineEntity machineEntity) {
        super(machineEntity.getCreatedDate(), machineEntity.getCreatedUserId(), machineEntity.getUpdatedDate(), machineEntity.getUpdatedUserId());
        this.machineId = machineEntity.getMachineId();
        this.machineName = machineEntity.getMachineName();
        this.machineIpAddress = machineEntity.getMachineIpAddress();
        this.machinePortNo = machineEntity.getMachinePortNo();
        this.machinePLCType = machineEntity.getMachinePLCType();
        this.machineMaxCapacity = machineEntity.getMachineMaxCapacity();
        this.remark = machineEntity.getRemark();
        this.statusCd = machineEntity.getStatusCd();
    }
}
