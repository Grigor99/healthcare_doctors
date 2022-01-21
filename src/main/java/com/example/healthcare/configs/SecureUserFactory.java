package com.example.healthcare.configs;


import com.example.healthcare.configs.utils.SecureUser;
import com.example.healthcare.documents.Doctors;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecureUserFactory {

    public static SecureUser create(Doctors doctors) {
        return new SecureUser(
                doctors.getId(),
                doctors.getUsername(),
                doctors.getPassword(),
                "",
                "",
                AuthorityUtils.commaSeparatedStringToAuthorityList(doctors.getAuthorities())
        );
    }


}
