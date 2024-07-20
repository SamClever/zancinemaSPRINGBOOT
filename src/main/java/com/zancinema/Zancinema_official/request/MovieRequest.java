package com.zancinema.Zancinema_official.request;

import java.sql.Date;

import com.zancinema.Zancinema_official.enums.Genre;
import com.zancinema.Zancinema_official.enums.Language;

import lombok.Data;



@Data
public class MovieRequest {
	private String movieName;
	private Integer duration;
	private Double rating;
	private Date releaseDate;
	private Genre genre;
	private Language language;
}
