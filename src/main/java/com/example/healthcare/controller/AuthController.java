package com.example.healthcare.controller;


import com.example.healthcare.util.dto.DoctorDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @PostMapping
    public void register(@RequestBody DoctorDto dto) {

    }
}
