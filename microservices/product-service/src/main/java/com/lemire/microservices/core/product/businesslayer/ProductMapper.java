package com.lemire.microservices.core.product.businesslayer;

import com.lemire.core.product.Product;
import com.lemire.microservices.core.product.datalayer.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "serviceAddress",ignore=true)
    Product entityToModel(ProductEntity entity);

    @Mappings({
            @Mapping(target = "id" , ignore = true),
            @Mapping(target = "version", ignore=true)
    })
    ProductEntity modelToEntity(Product model);
}
