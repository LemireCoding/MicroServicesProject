package com.lemire.api.composite.product;

public class ReviewSummary {
        private int reviewId;
        private String author;
        private String subject;

        public ReviewSummary(int reviewId, String author, String subject) {
                this.reviewId = reviewId;
                this.author = author;
                this.subject = subject;
        }

        public int getReviewId() {
                return this.reviewId;
        }

        public String getAuthor() {
                return this.author;
        }

        public String getSubject() {
                return this.subject;
        }
}
