package com.zancinema.Zancinema_official.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // Use LocalDateTime for timestamps
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TICKETS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private Integer totalTicketsPrice;

    private String bookedSeats;

    @CreationTimestamp
    private LocalDateTime bookedAt; // Use LocalDateTime instead of Date

    @ManyToOne
    @JoinColumn(name = "show_id") // Explicit column name
    private Show show;

    @ManyToOne
    @JoinColumn(name = "user_id") // Explicit column name
    private User user;

}
