package com.lemire.api.composite.product;


public class RecommendationSummary {

        private int recommendationId;
        private String author;
        private int rate;

        public RecommendationSummary(int recommendationId, String author, int rate) {
                this.recommendationId = recommendationId;
                this.author = author;
                this.rate = rate;
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
}
