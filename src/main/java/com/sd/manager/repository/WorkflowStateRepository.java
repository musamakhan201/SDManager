package com.sd.manager.repository;

import com.sd.manager.entity.WorkFlowState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowStateRepository extends JpaRepository<WorkFlowState,Long> {
    Optional<WorkFlowState> findByStateName(String state);
}
