package com.sd.manager.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "WorkflowState")
public class WorkFlowState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state_name", nullable = false)
    private String stateName;
}
