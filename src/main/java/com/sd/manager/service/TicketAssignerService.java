package com.sd.manager.service;

import com.sd.manager.entity.Ticket;
import com.sd.manager.entity.User;
import com.sd.manager.repository.TicketRepository;
import com.sd.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class TicketAssignerService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    public String distributeTicketsToAssignees(int totalTickets) {
        try {
            List<User> assignees = userRepository.findAll();
            if (assignees.isEmpty()) {
                throw new RuntimeException("No assignees found in the database.");
            }
            int remainingTickets = totalTickets % assignees.size();
            Iterator<User> assigneeIterator = assignees.iterator();

            for (int i = 0; i < totalTickets; i++) {
                User assignee = assigneeIterator.next();
                Ticket ticket = new Ticket();

                ticket.setAssignee(assignee.getUsername());
                ticketRepository.save(ticket);
                if (remainingTickets > 0) {
                    remainingTickets--;
                } else {
                    if (!assigneeIterator.hasNext()) {
                        assigneeIterator = assignees.iterator();
                    }
                }
            }
            return "Tickets Distributed";
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to distribute tickets: " + ex.getMessage());
        }
    }
}

