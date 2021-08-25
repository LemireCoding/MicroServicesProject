package com.lemire.core.recommendation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RecommendationServiceApi {

    @GetMapping(
            value = "/recommendation",
            produces = "application/json"
    )
    List<Recommendation> getRecommendations(@RequestParam(value = "productId",required = true) int proudctId);

    @PostMapping(
            value = "/recommendation",
            consumes = "application/json",
            produces = "application/json"
    )
    Recommendation createRecommendation(@RequestBody Recommendation model);

    @DeleteMapping(value="/recommendation")
    void deleteRecommendation(@RequestParam(value="productId", required = true) int productId);
}
