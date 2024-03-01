package com.sd.manager.service;

import com.sd.manager.entity.AuditLog;
import com.sd.manager.entity.Ticket;
import com.sd.manager.entity.WorkFlowState;
import com.sd.manager.repository.AuditLogRepository;
import com.sd.manager.repository.TicketRepository;
import com.sd.manager.repository.WorkflowStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private WorkflowStateRepository workflowStateRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    public ResponseEntity<String> updateTicketStatus(Long ticketId, String newStatus) {
        try {
            Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
            if (optionalTicket.isPresent()) {
                WorkFlowState state = workflowStateRepository.findByStateName(newStatus)
                        .orElseThrow(() -> new RuntimeException("Workflow status not found with name: " + newStatus));
                Ticket ticket = optionalTicket.get();
                ticket.setStatus(state.getStateName());
                audit(ticket);
                ticketRepository.save(ticket);
                return ResponseEntity.ok("Ticket status updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update ticket status: " + ex.getMessage());
        }
    }

    private void audit(Ticket ticket) {
        try {
            AuditLog auditLog = new AuditLog();
            auditLog.setTicketId(ticket.getId());
            auditLog.setUpdatedBy(ticket.getAssignee());
            auditLog.setTimestamp(LocalDateTime.now());
            auditLogRepository.save(auditLog);
        } catch (Exception e) {
            throw new RuntimeException("Unable to audit");
        }
    }

    public List<AuditLog> viewHistory() {
        try {
            List<AuditLog> logs = auditLogRepository.findAll();
            return logs;
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch history");
        }
    }

}
