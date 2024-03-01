package com.sd.manager.controller;

import com.sd.manager.entity.AuditLog;
import com.sd.manager.service.ReportService;
import com.sd.manager.service.TicketAssignerService;
import com.sd.manager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SDManagerController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketAssignerService ticketAssignerService;

    @PutMapping("/report/{reportId}/assignee")
    public ResponseEntity<String> setReportAssignee(@PathVariable Long reportId, @RequestBody String assignee) {
        return reportService.updateAssignee(reportId,assignee);
    }

    @PatchMapping("/ticket/{ticketId}/status")
    public ResponseEntity<String> updateTicketStatus(@PathVariable Long ticketId, @RequestBody String status) {
        return ticketService.updateTicketStatus(ticketId,status);
    }

    @PostMapping("/tickets/assign/{totalTickets}")
    public String assignTickets(@PathVariable int totalTickets){
        return ticketAssignerService.distributeTicketsToAssignees(totalTickets);
    }

    @GetMapping("/view/history")
    public List<AuditLog> viewHistory(){
        return ticketService.viewHistory();
    }
}
