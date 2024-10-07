package org.example.productservice.Mapper;

import org.example.productservice.DTO.ProductResponse;
import org.example.productservice.Entity.Product;
import org.example.productservice.Entity.ProductDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Productmaper {
    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toProductResponseList(List<Product> products);

    List<ProductResponse> toProductsResponse(List<ProductDocument> products);
}
