package com.lemire.core.recommendation;


public class Recommendation {

    private int productId;
    private int recommendationId;
    private String author;
    private int rate;
    private String content;
    private String serviceAddress;


    public Recommendation(int productId, int recommendationId, String author, int rate, String content, String serviceAddress) {
        this.productId = productId;
        this.recommendationId = recommendationId;
        this.author = author;
        this.rate = rate;
        this.content = content;
        this.serviceAddress = serviceAddress;
    }

    public Recommendation() {
        productId = 0;
        recommendationId = 0;
        author = null;
        rate=0;
        content = null;
        serviceAddress = null;
    }

    public int getProductId() {
        return this.productId;
    }

    public int getRecommendationId() {
        return this.recommendationId;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getRate() {
        return this.rate;
    }

    public String getContent() {
        return this.content;
    }

    public String getServiceAddress() {
        return this.serviceAddress;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }
}
