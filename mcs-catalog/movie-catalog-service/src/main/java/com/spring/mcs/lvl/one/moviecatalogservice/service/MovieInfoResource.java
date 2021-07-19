package com.spring.mcs.lvl.one.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.mcs.lvl.one.moviecatalogservice.model.CatalogItem;
import com.spring.mcs.lvl.one.moviecatalogservice.model.Movie;
import com.spring.mcs.lvl.one.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoResource {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject(
                "http://movie-info-service/movies/" + rating.getMovieId(),
                Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getMovieRating());
    }

    private CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie not found", "", 0);
    }

}
