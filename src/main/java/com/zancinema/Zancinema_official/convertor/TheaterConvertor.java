package com.zancinema.Zancinema_official.convertor;

import com.zancinema.Zancinema_official.entities.Theater;
import com.zancinema.Zancinema_official.request.TheaterRequest;

public class TheaterConvertor {

    public static Theater theaterDtoToTheater(TheaterRequest theaterRequest) {
        Theater theater = Theater.builder()
                .name(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .build();
        return theater;
    }
}
