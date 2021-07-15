package com.spring.mcs.lvl.one.moviecatalogservice.controller;

import com.spring.mcs.lvl.one.moviecatalogservice.model.CatalogItem;
import com.spring.mcs.lvl.one.moviecatalogservice.model.Movie;
import com.spring.mcs.lvl.one.moviecatalogservice.model.Rating;
import com.spring.mcs.lvl.one.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private RestTemplate restTemplate;
    private WebClient.Builder webClientBuilder;

    @Autowired
    public void setWebClientBuilder(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = restTemplate.getForObject(
                "http://localhost:8083/rating/users/" + userId,
                UserRating.class);

        List<Rating> ratings = userRating.getRatings();
        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject(
                    "http://localhost:8082/movies/" + rating.getMovieId(),
                    Movie.class);
            return new CatalogItem(movie.getName(), "desc", rating.getMovieRating());
        }).collect(Collectors.toList());
    }

}

/*Alternative WebClient way
            Movie movie =webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block(); // toWait
*/
