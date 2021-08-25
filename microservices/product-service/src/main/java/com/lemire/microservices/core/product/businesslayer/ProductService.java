package com.lemire.microservices.core.product.businesslayer;

import com.lemire.core.product.Product;

public interface ProductService {
    public Product getProductById(int productId);
    public Product createProduct(Product model);

    public void deleteProduct(int productId);
}
