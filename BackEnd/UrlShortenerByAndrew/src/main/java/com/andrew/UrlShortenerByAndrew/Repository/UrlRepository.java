package com.andrew.UrlShortenerByAndrew.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrew.UrlShortenerByAndrew.Model.Url;

public interface UrlRepository extends JpaRepository<Url, Long>{
	
	Url findByShortUrl(String shortUrl);
	
	Url findByLongUrl(String longUrl);
}
