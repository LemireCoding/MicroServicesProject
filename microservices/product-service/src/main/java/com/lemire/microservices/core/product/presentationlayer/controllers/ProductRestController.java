package com.lemire.microservices.core.product.presentationlayer.controllers;

import com.lemire.api.core.product.Product;
import com.lemire.api.core.product.ProductServiceApi;
import com.lemire.utils.exceptions.InvalidInputException;
import com.lemire.utils.exceptions.NotFoundException;
import com.lemire.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController implements ProductServiceApi {

    private static final Logger LOG = LoggerFactory.getLogger(ProductRestController.class);

    private final ServiceUtil serviceUtil;

    public ProductRestController(ServiceUtil serviceUtil){
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product getProduct(int productId){

        LOG.debug("/product MS returns the found product for productId: " + productId);

        if(productId < 1) throw new InvalidInputException("Invalid productId:" + productId);

        if(productId == 13) throw new NotFoundException("No product found for productID : " + productId);


        return new Product(productId,"name-"+productId, 123,serviceUtil.getServiceAddress());
    }
}
