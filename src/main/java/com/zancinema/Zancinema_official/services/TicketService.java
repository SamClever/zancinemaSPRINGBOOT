package com.zancinema.Zancinema_official.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zancinema.Zancinema_official.convertor.TicketConvertor;
import com.zancinema.Zancinema_official.entities.Show;
import com.zancinema.Zancinema_official.entities.ShowSeat;
import com.zancinema.Zancinema_official.entities.Ticket;
import com.zancinema.Zancinema_official.entities.User;
import com.zancinema.Zancinema_official.exceptions.SeatsNotAvailable;
import com.zancinema.Zancinema_official.exceptions.ShowDoesNotExists;
import com.zancinema.Zancinema_official.exceptions.UserDoesNotExists;
import com.zancinema.Zancinema_official.repositories.ShowRepository;
import com.zancinema.Zancinema_official.repositories.TicketRepository;
import com.zancinema.Zancinema_official.repositories.UserRepository;
import com.zancinema.Zancinema_official.request.TicketRequest;
import com.zancinema.Zancinema_official.response.TicketResponse;


@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private UserRepository userRepository;

	public TicketResponse ticketBooking(TicketRequest ticketRequest) {
		Optional<Show> showOpt = showRepository.findById(ticketRequest.getShowId());

		if (showOpt.isEmpty()) {
			throw new ShowDoesNotExists();
		}

		Optional<User> userOpt = userRepository.findById(ticketRequest.getUserId());

		if (userOpt.isEmpty()) {
			throw new UserDoesNotExists();
		}

		User user = userOpt.get();
		Show show = showOpt.get();

		Boolean isSeatAvailable = isSeatAvailable(show.getShowSeatList(), ticketRequest.getRequestSeats());

		if (!isSeatAvailable) {
			throw new SeatsNotAvailable();
		}

		// count price
		Integer getPriceAndAssignSeats = getPriceAndAssignSeats(show.getShowSeatList(),	ticketRequest.getRequestSeats());

		String seats = listToString(ticketRequest.getRequestSeats());

		Ticket ticket = new Ticket();
		ticket.setTotalTicketsPrice(getPriceAndAssignSeats);
		ticket.setBookedSeats(seats);
		ticket.setUser(user);
		ticket.setShow(show);

		ticket = ticketRepository.save(ticket);

		user.getTicketList().add(ticket);
		show.getTicketList().add(ticket);
		userRepository.save(user);
		showRepository.save(show);

		return TicketConvertor.returnTicket(show, ticket);
	}

	private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
		for (ShowSeat showSeat : showSeatList) {
			String seatNo = showSeat.getSeatNo();

			if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
				return false;
			}
		}

		return true;
	}

	private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
		Integer totalAmount = 0;

		for (ShowSeat showSeat : showSeatList) {
			if (requestSeats.contains(showSeat.getSeatNo())) {
				totalAmount += showSeat.getPrice();
				showSeat.setIsAvailable(Boolean.FALSE);
			}
		}

		return totalAmount;
	}

	private String listToString(List<String> requestSeats) {
		StringBuilder sb = new StringBuilder();

		for (String s : requestSeats) {
			sb.append(s).append(",");
		}

		return sb.toString();
	}

}
