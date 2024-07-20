package com.zancinema.Zancinema_official.convertor;

import com.zancinema.Zancinema_official.entities.Show;
import com.zancinema.Zancinema_official.entities.Ticket;
import com.zancinema.Zancinema_official.response.TicketResponse;

public class TicketConvertor {

    public static TicketResponse returnTicket(Show show, Ticket ticket) {
        TicketResponse ticketResponseDto = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .address(show.getTheater().getAddress())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .date(show.getDate())
                .time(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();

        return ticketResponseDto;
    }
}
