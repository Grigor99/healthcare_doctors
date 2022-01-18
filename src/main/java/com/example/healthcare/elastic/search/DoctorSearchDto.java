package com.example.healthcare.elastic.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSearchDto implements Serializable {

    private String firstName;
    private String lastName;
    private String biography;
    private Integer experience;

}
