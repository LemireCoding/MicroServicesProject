package com.lemire.microservices.core.recommendation.presentationlayer.controllers;


import com.lemire.api.core.recommendation.Recommendation;
import com.lemire.api.core.recommendation.RecommendationServiceApi;
import com.lemire.utils.exceptions.InvalidInputException;
import com.lemire.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecommendationRESTController implements RecommendationServiceApi {

    private static final Logger LOG = LoggerFactory.getLogger(RecommendationRESTController.class);

    private final ServiceUtil serviceUtil;

    public RecommendationRESTController(ServiceUtil serviceUtil){
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId){
        if(productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if(productId == 113){
            LOG.debug("No recommendations found for productId : {} " + productId);
            return new ArrayList<>();
        }


        List<Recommendation> listRecommendations = new ArrayList<>();
        listRecommendations.add(new Recommendation(productId,1,"author 1",1,"Content 1", serviceUtil.getServiceAddress()));
        listRecommendations.add(new Recommendation(productId,2,"author 2",2,"Content 2", serviceUtil.getServiceAddress()));
        listRecommendations.add(new Recommendation(productId,3,"author 3",3,"Content 3", serviceUtil.getServiceAddress()));

        LOG.debug("/recommendations found response size : {}",listRecommendations);
        return listRecommendations;
    }

}
