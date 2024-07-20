package com.zancinema.Zancinema_official.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.zancinema.Zancinema_official.entities.Theater;


public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);
}
