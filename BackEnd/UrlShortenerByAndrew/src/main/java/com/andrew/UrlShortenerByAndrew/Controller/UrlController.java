package com.andrew.UrlShortenerByAndrew.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.andrew.UrlShortenerByAndrew.Model.Url;
import com.andrew.UrlShortenerByAndrew.Model.UrlRequest;
import com.andrew.UrlShortenerByAndrew.Service.UrlService;

@RestController
@CrossOrigin
public class UrlController {
	
	@Autowired
	UrlService service;
	
	@PostMapping("/shortenUrl")
	public ResponseEntity<?> getShortUrl(@RequestBody UrlRequest req) {
	    try {
	        String longUrl = req.getLongUrl();
	        String alias = req.getAlias();

	        Url url = service.generateShortUrl(longUrl, alias);
	        String shortUrl = url.getShortUrl();
	        return ResponseEntity.ok("http://localhost:8080/" + shortUrl);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Alias already exists!");
	    }
	}

	
	@GetMapping("/{shortUrl}")
	public ResponseEntity<String>  getLongUrl(@PathVariable String shortUrl) {
		Url url =service.getLongUrl(shortUrl);
		if(url!=null) 
			return ResponseEntity.status(HttpStatus.FOUND).header("Location", url.getLongUrl()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oops! You've entered a wrong link!");
	}
}
