package com.lemire.microservices.composite.product.integrationlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemire.api.core.product.Product;
import com.lemire.api.core.product.ProductServiceApi;
import com.lemire.api.core.recommendation.Recommendation;
import com.lemire.api.core.recommendation.RecommendationServiceApi;
import com.lemire.api.core.review.Review;
import com.lemire.api.core.review.ReviewServiceApi;
import com.lemire.utils.exceptions.InvalidInputException;
import com.lemire.utils.exceptions.NotFoundException;
import com.lemire.utils.http.HttpErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductCompositeIntegration implements ProductServiceApi, RecommendationServiceApi, ReviewServiceApi {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    private final String productServiceUrl;

    private final String recommendationServiceUrl;

    private final String reviewServiceUrl;

    public ProductCompositeIntegration
            (
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("${app.product-service.host") String productServiceHost,
            @Value("${app.product-service.port") String productServicePort,

            @Value("${app.recommendation-service.host") String recommendationServiceHost,
            @Value("${app.recommendation-service.port") String recommendationServicePort,

            @Value("${app.review-service.host") String reviewServiceHost,
            @Value("${app.review-service.port") String reviewServicePort
            )
            {
                this.restTemplate = restTemplate;
                this.mapper = mapper;
                productServiceUrl = "http://" +productServiceHost + ":"+ productServicePort + "/product/";
                recommendationServiceUrl = "http://" +recommendationServiceHost + ":"+ recommendationServicePort + "/recommendation?productId=";
                reviewServiceUrl = "http://" +reviewServiceHost + ":"+ reviewServicePort + "/review/";
            }

    @Override
    public Product getProduct(int productId) {


        try{
            String url = productServiceUrl + productId;
            LOG.debug("Will call get product api on rul : {}", url);
            Product product = restTemplate.getForObject(url,Product.class);
            LOG.debug("Found a product with id : {}", product.getProductId());
            return product;
        } catch(HttpClientErrorException ex){
            switch(ex.getStatusCode()){
                case NOT_FOUND: throw new NotFoundException(getErrorMessage(ex));
                case UNPROCESSABLE_ENTITY: throw new InvalidInputException(getErrorMessage(ex));
                default:
                    LOG.warn("Got an unexpected http error: {}, will rethrow it", ex.getStatusCode());
                    LOG.warn("Error body:{}", ex.getResponseBodyAsString());
                    throw ex;
            }
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try{
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch(IOException ioex){
            return ioex.getMessage();
        }
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        try{
            String url = recommendationServiceUrl + productId;
            LOG.debug("will call getRecommendations API on URL : {}",url);
            List<Recommendation> recommendations = restTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recommendation>>(){}).getBody();

            LOG.debug("Found {} recommendations for a product id : {}", recommendations.size(), productId);
        return recommendations;
        }
        catch(Exception e){
            LOG.warn("Got an exception while requesting recommendations, return zero recommendations : {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Review> getReviews(int productId) {
        try{
            String url = reviewServiceUrl + productId;
            LOG.debug("will call getReviews API on URL : {}",url);
            List<Review> reviews = restTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>(){}).getBody();

            LOG.debug("Found {} reviews for a product id : {}", reviews.size(), productId);
            return reviews;
        }
        catch(Exception e){
            LOG.warn("Got an exception while requesting reviews, return zero reviews : {}", e.getMessage());
            return new ArrayList<>();
        }
    }
}
