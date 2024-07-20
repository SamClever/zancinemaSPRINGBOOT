package com.zancinema.Zancinema_official.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zancinema.Zancinema_official.convertor.ShowConverter;
import com.zancinema.Zancinema_official.entities.Movie;
import com.zancinema.Zancinema_official.entities.Show;
import com.zancinema.Zancinema_official.entities.ShowSeat;
import com.zancinema.Zancinema_official.entities.Theater;
import com.zancinema.Zancinema_official.entities.TheaterSeat;
import com.zancinema.Zancinema_official.enums.SeatType;
import com.zancinema.Zancinema_official.exceptions.MovieDoesNotExists;
import com.zancinema.Zancinema_official.exceptions.ShowDoesNotExists;
import com.zancinema.Zancinema_official.exceptions.TheaterDoesNotExists;
import com.zancinema.Zancinema_official.repositories.MovieRepository;
import com.zancinema.Zancinema_official.repositories.ShowRepository;
import com.zancinema.Zancinema_official.repositories.TheaterRepository;
import com.zancinema.Zancinema_official.request.ShowRequest;
import com.zancinema.Zancinema_official.request.ShowSeatRequest;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    public String addShow(ShowRequest showRequest) {
        Show show = ShowConverter.showDtoToShow(showRequest);

        Optional<Movie> movieOpt = movieRepository.findById(showRequest.getMovieId());

        if (movieOpt.isEmpty()) {
            throw new MovieDoesNotExists();
        }

        Optional<Theater> theaterOpt = theaterRepository.findById(showRequest.getTheaterId());

        if (theaterOpt.isEmpty()) {
            throw new TheaterDoesNotExists();
        }

        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get();

        show.setMovie(movie);
        show.setTheater(theater);
        show = showRepository.save(show);

        movie.getShows().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show has been added Successfully";
    }

    public String associateShowSeats(ShowSeatRequest showSeatRequest) throws ShowDoesNotExists {
        Optional<Show> showOpt = showRepository.findById(showSeatRequest.getShowId());

        if (showOpt.isEmpty()) {
            throw new ShowDoesNotExists();
        }

        Show show = showOpt.get();
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for (TheaterSeat theaterSeat : theaterSeatList) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if (showSeat.getSeatType().equals(SeatType.CLASSIC)) {
                showSeat.setPrice(showSeatRequest.getPriceOfClassicSeat());
            } else {
                showSeat.setPrice(showSeatRequest.getPriceOfPremiumSeat());
            }

            showSeat.setShow(show);
            showSeat.setIsAvailable(Boolean.TRUE);
            showSeat.setIsFoodContains(Boolean.FALSE);

            showSeatList.add(showSeat);
        }

        showRepository.save(show);

        return "Show seats have been associated successfully";
    }

	public String addShow(SavedRequest showRequest) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addShow'");
	}
}
