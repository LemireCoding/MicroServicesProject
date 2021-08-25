package com.lemire.microservices.composite.product.presentationlayer.controllers;

import com.lemire.api.composite.product.*;
import com.lemire.api.core.product.Product;
import com.lemire.api.core.recommendation.Recommendation;
import com.lemire.api.core.review.Review;
import com.lemire.microservices.composite.product.integrationlayer.ProductCompositeIntegration;
import com.lemire.utils.exceptions.NotFoundException;
import com.lemire.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;


public class ProductCompositeRESTController implements ProductCompositeServiceAPI {

    private ProductCompositeIntegration integration;
    private final ServiceUtil serviceUtil;

    public ProductCompositeRESTController(ProductCompositeIntegration integration, ServiceUtil serviceUtil) {
        this.integration = integration;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public ProductAggregate getProduct(int productId) {
        Product product = integration.getProduct(productId);
        if(product == null) throw new NotFoundException("No product found for productId : "+productId);


        List<Recommendation> recommendations = integration.getRecommendations(productId);

        List<Review> reviews = integration.getReviews(productId);

        return createProductAggregate(product, recommendations,reviews,serviceUtil.getServiceAddress());
    }

    private ProductAggregate createProductAggregate(Product product, List<Recommendation> recommendations, List<Review> reviews, String serviceAddress) {

        int productId = product.getProductId();
        String name = product.getName();
        int weight = product.getWeight();

        List<RecommendationSummary> recommendationSummaries = (recommendations == null) ? null :
                recommendations.stream().map(recommendation -> new RecommendationSummary(
                        recommendation.getRecommendationId(),
                        recommendation.getAuthor(),
                        recommendation.getRate()))
                .collect(Collectors.toList());

        List<ReviewSummary> reviewSummaries = (reviews == null) ? null: reviews.stream().map(review -> new ReviewSummary(
                review.getReviewId(),
                review.getAuthor(),
                review.getSubject()))
           .collect(Collectors.toList());


        String productAddress = product.getServiceAddress();
        String recommendationAddress = (recommendations != null && recommendations.size() >0 )? recommendations.get(0).getServiceAddress() : "";
        String reviewAddress = (reviews != null && reviews.size() >0 )? reviews.get(0).getServiceAddress() : "";
        ServiceAddress serviceAddresses = new ServiceAddress(serviceAddress,productAddress,reviewAddress,recommendationAddress);

        return new ProductAggregate(productId,name,weight,recommendationSummaries,reviewSummaries,serviceAddresses);
    }
}
