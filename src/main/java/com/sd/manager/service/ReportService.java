package com.sd.manager.service;

import com.sd.manager.entity.Report;
import com.sd.manager.entity.User;
import com.sd.manager.repository.ReportRepository;
import com.sd.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<String> updateAssignee(Long reportId, String assignee) {
        try {
            Optional<Report> optionalReport = reportRepository.findById(reportId);
            if (optionalReport.isPresent()) {
                User assigneeUser = userRepository.findByUsername(assignee)
                        .orElseThrow(() -> new RuntimeException("User not found with username: " + assignee));
                Report report = optionalReport.get();
                report.setAssignee(assigneeUser);
                reportRepository.save(report);
                return ResponseEntity.ok("Assignee updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update assignee: " + ex.getMessage());
        }
    }
}
