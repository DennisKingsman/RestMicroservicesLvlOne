package com.spring.mcs.lvl.one.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.mcs.lvl.one.moviecatalogservice.model.Rating;
import com.spring.mcs.lvl.one.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static com.spring.mcs.lvl.one.moviecatalogservice.controller.MovieCatalogResource.*;

@Service
public class RatingDataResource {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
            threadPoolKey = "ratingDataPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            },
            commandProperties = {
                    @HystrixProperty(name = HYSTRIX_TIMEOUT, value = "2500"),
                    @HystrixProperty(name = HYSTRIX_THRESHOLD, value = "5"),
                    @HystrixProperty(name = HYSTRIX_SLEEP, value = "5000"),
                    @HystrixProperty(name = HYSTRIX_ERROR_PERCENT, value = "60")
            }
    )
    public UserRating getUserRating(String userId) {
        System.out.println("WE ARE IN getUserRating");
        return restTemplate.getForObject(
                "http://rating-data-service/rating/users/" + userId,
                UserRating.class);
    }

    private UserRating getFallbackUserRating(String userId) {
        return new UserRating(userId, Arrays.asList(new Rating("0", 0)));
    }

}
