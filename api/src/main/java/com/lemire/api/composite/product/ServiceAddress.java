package com.lemire.api.composite.product;


public class ServiceAddress {

    private final String compositeAddress;
    private final String productAddress;
    private final String reviewAddress;
    private final String recommendationAddress;

    public ServiceAddress(String compositeAddress, String productAddress, String reviewAddress, String recommendationAddress) {
        this.compositeAddress = compositeAddress;
        this.productAddress = productAddress;
        this.reviewAddress = reviewAddress;
        this.recommendationAddress = recommendationAddress;
    }

    public ServiceAddress(){
        compositeAddress = null;
        productAddress = null;
        reviewAddress = null;
        recommendationAddress = null;
    }

    public String getCompositeAddress() {
        return this.compositeAddress;
    }

    public String getProductAddress() {
        return this.productAddress;
    }

    public String getReviewAddress() {
        return this.reviewAddress;
    }

    public String getRecommendationAddress() {
        return this.recommendationAddress;
    }
}
