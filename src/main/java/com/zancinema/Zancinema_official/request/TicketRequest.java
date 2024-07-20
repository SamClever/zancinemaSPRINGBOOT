package com.zancinema.Zancinema_official.request;


import java.util.List;

import lombok.Data;

@Data
public class TicketRequest {
    private Integer showId;
    private Integer userId;
    private List<String> requestSeats;
}
