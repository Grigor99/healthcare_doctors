package com.example.healthcare.util.dto;

import com.example.healthcare.document.Availability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto implements Serializable {
    private String specialCode;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String specialProfession;
    private String awards;
    private String biography;
    private Availability availability;
}
