package com.spring.mcs.lvl.one.moviecatalogservice.controller;

import com.spring.mcs.lvl.one.moviecatalogservice.model.CatalogItem;
import com.spring.mcs.lvl.one.moviecatalogservice.model.Rating;
import com.spring.mcs.lvl.one.moviecatalogservice.model.UserRating;
import com.spring.mcs.lvl.one.moviecatalogservice.service.MovieInfoResource;
import com.spring.mcs.lvl.one.moviecatalogservice.service.RatingDataResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    public static final String HYSTRIX_TIMEOUT = "execution.isolation.thread.timeoutInMilliseconds";
    public static final String HYSTRIX_THRESHOLD = "circuitBreaker.requestVolumeThreshold";
    public static final String HYSTRIX_ERROR_PERCENT = "circuitBreaker.errorThresholdPercentage";
    public static final String HYSTRIX_SLEEP = "circuitBreaker.sleepWindowInMilliseconds";

    private RatingDataResource ratingDataResource;
    private MovieInfoResource movieInfoResource;
    private WebClient.Builder webClientBuilder;
    private DiscoveryClient discoveryClient;

    @Autowired
    public void setRatingDataResource(RatingDataResource ratingDataResource) {
        this.ratingDataResource = ratingDataResource;
    }

    @Autowired
    public void setMovieInfoResource(MovieInfoResource movieInfoResource) {
        this.movieInfoResource = movieInfoResource;
    }

    //use for advanced load balancing
    @Autowired
    public void setDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    //use for reactive-web calls
    @Autowired
    public void setWebClientBuilder(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = ratingDataResource.getUserRating(userId);
        List<Rating> ratings = userRating.getRatings();
        return ratings.stream()
                .map(rating -> movieInfoResource.getCatalogItem(rating))
                .collect(Collectors.toList());
    }

    @Deprecated
    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItem("No movie", "", 0));
    }

}

/*Alternative WebClient way
            Movie movie =webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block(); // toWait sync
*/
