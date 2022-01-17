package com.example.healthcare.controller;


import com.example.healthcare.service.AuthService;
import com.example.healthcare.util.dto.DoctorDto;
import com.example.healthcare.util.exceptionhandler.exceptions.DuplicateException;
import com.example.healthcare.util.exceptionhandler.exceptions.UnauthorizedException;
import com.example.healthcare.util.exceptionhandler.exceptions.WrongCodeException;
import com.example.healthcare.util.uri.AuthUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = AuthUri.REGISTER)
    public ResponseEntity<?> register(@RequestBody DoctorDto dto) throws DuplicateException, UnauthorizedException {
        authService.sign_up(dto);
        return ResponseEntity.ok("SIGNED_UP");
    }

    @PostMapping(value = AuthUri.REGISTER_CONFIRM)
    public ResponseEntity<?> confirmRegister(@PathVariable(value = "code") String code) throws WrongCodeException {
        return ResponseEntity.ok(authService.confirmRegister(code));
    }

}
