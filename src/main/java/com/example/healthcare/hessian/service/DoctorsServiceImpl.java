package com.example.healthcare.hessian.service;


import com.example.healthcare.documents.Doctors;
import com.example.healthcare.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class DoctorsServiceImpl implements DoctorsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Override
    public Doctors getById(String id)  {
        return doctorsRepository.getById(id);

    }

}
