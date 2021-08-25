package com.lemire.api.core.recommendation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RecommendationServiceApi {

    @GetMapping(
            value = "/recommendation",
            produces = "application/json"
    )
    List<Recommendation> getRecommendations(@RequestParam(value = "productId",required = true) int proudctId);
}
