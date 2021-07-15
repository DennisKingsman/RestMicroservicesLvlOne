package com.spring.mcs.lvl.one.retingdataservice.resource;

import com.spring.mcs.lvl.one.retingdataservice.model.Rating;
import com.spring.mcs.lvl.one.retingdataservice.model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingDataController {

    @GetMapping("/{movieId}")
    public Rating getRatingData(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    /**
     * @param userId use to get data from DB
     * @return list of ratings
     */
    @GetMapping("/users/{userId}")
    public UserRating getRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1", 4),
                new Rating("2", 3)
        );
        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);
        return userRating;
    }

}
