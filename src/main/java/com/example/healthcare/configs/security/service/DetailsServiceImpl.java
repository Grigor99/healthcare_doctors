package com.example.healthcare.configs.security.service;


import com.example.healthcare.configs.SecureUserFactory;
import com.example.healthcare.document.Doctors;
import com.example.healthcare.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Doctors doctor = null;

        if (username != null) {
            doctor = this.doctorsRepository.findByUsernameAndRemovedFalse(username);
        }

        if (doctor == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        return SecureUserFactory.create(doctor);


    }
}

