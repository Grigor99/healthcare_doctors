package com.example.healthcare.elastic.index;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(indexName = "docs")
public class Docs implements Serializable {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String firstName;
    @Field(type = FieldType.Text)
    private String lastName;
    @Field(type = FieldType.Text)
    private String biography;
    @Field(type = FieldType.Integer)
    private Integer experience;
    @Field(type = FieldType.Keyword)
    private String specialProfession;
    @Field(type = FieldType.Keyword)
    private String day;
    @Field(type = FieldType.Long)
    private Long availableFrom;
    @Field(type = FieldType.Long)
    private Long availableTo;
}


