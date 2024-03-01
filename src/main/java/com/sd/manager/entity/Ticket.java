package com.sd.manager.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "assignee", nullable = false)
    private String assignee;

    @Column(name = "technical_feedback")
    private String technicalFeedback;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Column(name = "updating_date", nullable = false)
    private Date updatingDate;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;
}
