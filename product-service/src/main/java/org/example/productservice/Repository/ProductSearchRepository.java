package org.example.productservice.Repository;



import org.example.productservice.Entity.Product;
import org.example.productservice.Entity.ProductDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


import java.util.List;

//@EnableElasticsearchRepositories
public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, Long> {
    List<Product> findByNameContaining(String keyword);

    List<ProductDocument> findByCategoryContaining(String keyword);


    @Query("{\"wildcard\": { \"category\": { \"value\": \"*?0*\" }}}")
    List<Product> findByCategoryWildcard(String keyword);
}