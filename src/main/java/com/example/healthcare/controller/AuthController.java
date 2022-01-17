package com.example.healthcare.controller;


import com.example.healthcare.util.dto.DoctorDto;
import com.example.healthcare.util.uri.AuthUri;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @PostMapping(value = AuthUri.REGISTER)
    public void register(@RequestBody DoctorDto dto) {

    }
}
