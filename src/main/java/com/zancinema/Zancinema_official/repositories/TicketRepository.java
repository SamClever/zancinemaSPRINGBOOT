package com.zancinema.Zancinema_official.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zancinema.Zancinema_official.entities.Ticket;



public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
