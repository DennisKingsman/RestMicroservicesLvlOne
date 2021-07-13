package com.spring.mcs.lvl.one.retingdataservice.resource;

import com.spring.mcs.lvl.one.retingdataservice.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingDataController {

    @GetMapping("/{movieId}")
    public Rating getRatingData(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

}
