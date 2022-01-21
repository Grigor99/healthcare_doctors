package com.example.healthcare.documents;


import com.example.healthcare.configs.utils.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;
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

    @Version
    private Long version;


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


    @Field
    private Availability availability;

    @Transient
    @JsonIgnore
    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @Transient
    @LastModifiedDate
    @Field(name = "last_modified")
    private Instant lastModified;

    @Field(name = "removed")
    private boolean removed;
    @Field(name = "experience")
    private Integer experience;

    @Field(name = "doctor_status")
    private DOCTOR_STATUS doctorStatus;

    @Field(name = "authorities")
    private String authorities = UserType.DOCTOR.getLabel();
    @Field(name = "doctoral_code")
    private String doctoralCode = "doctors_code_8988998991111_UUUU_LLL)*(&(((JKHJH%";
    @Field(name = "email_code")
    private String emailCode;

    public enum DOCTOR_STATUS {
        REGISTERED, ACTIVE, BLOCKED;
    }

    public Doctors(String firstName, String lastName, String username, String password, String specialProfession, String awards, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.specialProfession = specialProfession;
        this.awards = awards;
        this.biography = biography;
    }
}
