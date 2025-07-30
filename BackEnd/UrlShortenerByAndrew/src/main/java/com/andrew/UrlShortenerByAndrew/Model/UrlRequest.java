package com.andrew.UrlShortenerByAndrew.Model;

import lombok.Data;

@Data
public class UrlRequest {
	public String longUrl;
	public String alias;
}
