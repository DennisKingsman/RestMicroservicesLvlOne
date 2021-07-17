package com.spring.mcs.lvl.one.movieinfoservice.resource;

import com.spring.mcs.lvl.one.movieinfoservice.model.Movie;
import com.spring.mcs.lvl.one.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    private static String MOVIE_DB_URL = "https://api.themoviedb.org/3/movie/";

    @Value("${api.key}")
    private String apiKey;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        String url = MOVIE_DB_URL + movieId + "?api_key=" + apiKey;
        System.out.println(url);
        MovieSummary movieSummary = restTemplate.getForObject(
                url,
                MovieSummary.class);
        return new Movie(
                movieId,
                movieSummary.getTitle(),
                movieSummary.getOverview()
        );
    }

}
