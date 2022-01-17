package com.example.healthcare.document;


import com.example.healthcare.configs.utils.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "doctors")
public class Doctors implements Serializable {
    @Id
    private String id;

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    private String lastName;

    @Field(name = "username")
    private String username;

    @Field(name = "password")
    private String password;

    @Field(name = "special_profession")
    private String specialProfession;

    @Field(name = "awards")
    private String awards;

    @Field(name = "biography")
    private String biography;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "last_modified")
    private Instant lastModified;

    @Field(name = "is_removed")
    private Boolean isRemoved;

    @Field(name = "authorities")
    private String authorities = UserType.DOCTOR.getLabel();

}
