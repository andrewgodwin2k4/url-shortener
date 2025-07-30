package com.andrew.UrlShortenerByAndrew.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrew.UrlShortenerByAndrew.Model.Url;
import com.andrew.UrlShortenerByAndrew.Repository.UrlRepository;

@Service
public class UrlService {
	
	@Autowired
	UrlRepository repo;
	
	public Url generateShortUrl(String longUrl, String alias) {
		
		if(!longUrl.startsWith("http://") && !longUrl.startsWith("https://"))
			longUrl = "https://"+longUrl;
		Url exist = repo.findByLongUrl(longUrl);
		
		if(exist!=null) {
			if(alias!=null && !alias.isBlank()) {
				exist.setShortUrl(alias);
				repo.save(exist);
			}
			return exist;
		}
	
		String shortUrl="";
		if(alias!=null && !alias.isBlank()) {
			Url aliasExist = repo.findByShortUrl(alias);
			if(aliasExist!=null) 
				throw new RuntimeException("Alias already in use. Kindly choose a different one!");
			shortUrl = alias;	
		}
			
		else {
			shortUrl = createShortUrl();
			while(repo.findByShortUrl(shortUrl)!=null)
				shortUrl = createShortUrl();
		}
		
		
		Url fullUrl = new Url();
		fullUrl.setLongUrl(longUrl);
		fullUrl.setShortUrl(shortUrl);
		repo.save(fullUrl);
		
		return fullUrl;
	}
	
	public String createShortUrl() {
		StringBuilder shortUrl = new StringBuilder();
		Random rand = new Random();
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int len = chars.length();
		for(int i=0;i<6;i++) 
			shortUrl.append(chars.charAt(rand.nextInt(0,len)));
		return shortUrl.toString();
	}

	public Url getLongUrl(String shortUrl) {
		return repo.findByShortUrl(shortUrl);
	}
}
