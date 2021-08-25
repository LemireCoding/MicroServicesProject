package com.lemire.microservices.core.review.presentationlayer.controllers;

import com.lemire.api.core.review.Review;
import com.lemire.api.core.review.ReviewServiceApi;
import com.lemire.utils.exceptions.InvalidInputException;
import com.lemire.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewRestController implements ReviewServiceApi {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewRestController.class);

    private final ServiceUtil serviceUtil;

    public ReviewRestController(ServiceUtil serviceUtil){
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Review> getReviews(int productId){
        if(productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if(productId == 113){
            LOG.debug("No listReviews found for productId : {} " + productId);
            return new ArrayList<>();
        }


        List<Review> listReviews = new ArrayList<>();
        listReviews.add(new Review(productId,1,"author 1","1","Content 1", serviceUtil.getServiceAddress()));
        listReviews.add(new Review(productId,2,"author 2","!","Content 2", serviceUtil.getServiceAddress()));
        listReviews.add(new Review(productId,3,"author 3","2","Content 3", serviceUtil.getServiceAddress()));

        LOG.debug("/listReviews found response size : {}",listReviews);
        return listReviews;
    }


}
