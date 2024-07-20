package com.zancinema.Zancinema_official.request;


import java.sql.Date;

import lombok.Data;

@Data
public class ShowTimingsRequest {
    private Date date;
    private Integer theaterId;
    private Integer movieId;
}
