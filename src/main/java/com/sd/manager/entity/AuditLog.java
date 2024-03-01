package com.sd.manager.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
