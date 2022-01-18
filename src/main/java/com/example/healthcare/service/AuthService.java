package com.example.healthcare.service;

import com.example.healthcare.util.dto.DoctorDto;
import com.example.healthcare.util.exceptionhandler.exceptions.DuplicateException;
import com.example.healthcare.util.exceptionhandler.exceptions.NotFoundException;
import com.example.healthcare.util.exceptionhandler.exceptions.UnauthorizedException;
import com.example.healthcare.util.exceptionhandler.exceptions.WrongCodeException;

import java.util.Map;

public interface AuthService {
    void sign_up(DoctorDto dto) throws DuplicateException, UnauthorizedException;

    Map<String, String> confirmRegister(String code) throws WrongCodeException;

    Map<String, String> signIn(String username, String password) throws NotFoundException, UnauthorizedException;
}
