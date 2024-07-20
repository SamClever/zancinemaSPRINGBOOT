package com.zancinema.Zancinema_official.convertor;

import com.zancinema.Zancinema_official.entities.Show;
import com.zancinema.Zancinema_official.request.ShowRequest;

public class ShowConverter {

    public static Show showDtoToShow(ShowRequest showRequest) {
        return Show.builder()
                .time(showRequest.getShowStartTime())
                .date(showRequest.getShowDate())
                .build();
    }
}
