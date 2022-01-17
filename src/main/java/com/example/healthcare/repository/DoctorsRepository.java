package com.example.healthcare.repository;

import com.example.healthcare.document.Doctors;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends MongoRepository<Doctors, String> {
    Doctors findByUsernameAndIsRemovedFalse(String username);
}
