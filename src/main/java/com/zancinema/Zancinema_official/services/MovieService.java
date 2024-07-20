package com.zancinema.Zancinema_official.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zancinema.Zancinema_official.convertor.MovieConvertor;
import com.zancinema.Zancinema_official.entities.Movie;
import com.zancinema.Zancinema_official.exceptions.MovieAlreadyExist;
import com.zancinema.Zancinema_official.repositories.MovieRepository;
import com.zancinema.Zancinema_official.request.MovieRequest;


@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public String addMovie(MovieRequest movieRequest) {
		Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());
		
		if (movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())) {
			throw new MovieAlreadyExist();
		}
		
		Movie movie = MovieConvertor.movieDtoToMovie(movieRequest);
		
		movieRepository.save(movie);
		return "The movie has been added successfully";
	}

}
