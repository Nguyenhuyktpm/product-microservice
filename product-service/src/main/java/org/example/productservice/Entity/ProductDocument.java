package org.example.productservice.Entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.math.BigDecimal;
@Data
@Builder

@Document(indexName = "product")
public class ProductDocument {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String category;
    @Field(type = FieldType.Double)
    private double price;
}
