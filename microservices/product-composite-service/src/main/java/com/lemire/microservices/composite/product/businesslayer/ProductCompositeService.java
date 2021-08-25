package com.lemire.microservices.composite.product.businesslayer;

import com.lemire.composite.product.ProductAggregate;

public interface ProductCompositeService {
    public ProductAggregate getProduct(int productId);
    public void createProduct(ProductAggregate model);
    public void deleteProduct(int productId);
}
