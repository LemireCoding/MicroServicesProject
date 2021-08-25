package com.lemire.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductServiceApi {

    @GetMapping(
            value = "/product/{productId}",
            produces = "application/json"
    )
    Product getProduct(@PathVariable int productId);
}
