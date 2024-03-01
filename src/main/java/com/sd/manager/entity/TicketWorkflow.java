package com.sd.manager.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "TicketWorkflow")
public class TicketWorkflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "workflow_state_id", nullable = false)
    private WorkFlowState workflowState;

    @Column(name = "transition_date", nullable = false)
    private Date transitionDate;
}
