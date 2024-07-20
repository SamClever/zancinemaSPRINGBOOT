package com.zancinema.Zancinema_official.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zancinema.Zancinema_official.entities.Movie;



public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Movie findByMovieName(String name);
}