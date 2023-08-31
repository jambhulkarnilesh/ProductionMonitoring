package com.iot.pmonitor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId;

    @Column(name = "PART_NAME")
    private String partName;


    @Builder(builderMethodName = "partEntityBuilder")
    public PartEntity(String partName, Instant createdDate, String createdUserId,  Instant updatedDate, String updatedUserId) {
        super(createdDate, createdUserId,  updatedDate, updatedUserId);
        this.partName = partName;
    }
}
