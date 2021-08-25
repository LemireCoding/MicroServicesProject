package com.lemire.microservices.core.recommendation.businesslayer;

import com.lemire.core.recommendation.Recommendation;

import java.util.List;

public interface RecommendationService {
    public List<Recommendation> getByProductId(int productId);
    public Recommendation createRecommendation(Recommendation model);
    public void deleteRecommendations(int productId);
}
