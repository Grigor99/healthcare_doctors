package com.example.healthcare.service;

import com.example.healthcare.configs.AuthToken;
import com.example.healthcare.configs.security.service.DetailsService;
import com.example.healthcare.configs.utils.TokenUtils;
import com.example.healthcare.configs.utils.UserType;
import com.example.healthcare.document.Doctors;
import com.example.healthcare.repository.DoctorsRepository;
import com.example.healthcare.util.dto.DoctorDto;
import com.example.healthcare.util.exceptionhandler.ExceptionHandler;
import com.example.healthcare.util.exceptionhandler.exceptions.DuplicateException;
import com.example.healthcare.util.exceptionhandler.exceptions.NotFoundException;
import com.example.healthcare.util.exceptionhandler.exceptions.UnauthorizedException;
import com.example.healthcare.util.exceptionhandler.exceptions.WrongCodeException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AuthServiceImpl implements AuthService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${security.accesstoken.secret}")
    protected String accessTokenSecret;


    @Value("${security.accesstoken.expiration}")
    protected long accessTokenExpiration;

    @Value("${security.refreshtoken.header}")
    protected String refreshTokenHeader;

    @Value("${security.refreshtoken.secret}")
    protected String refreshTokenSecret;

    @Value("${security.refreshtoken.expiration}")
    protected long refreshTokenExpiration;


    @Autowired
    private DetailsService detailsService;
    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void sign_up(DoctorDto dto) throws DuplicateException, UnauthorizedException {
        DuplicateException.check(doctorsRepository.existsByUsernameAndRemovedFalse(dto.getUsername()), "duplicate account");
        UnauthorizedException.check(!dto.getSpecialCode().equals("doctors_code_8988998991111_UUUU_LLL)*(&(((JKHJH%"), "wrong code");
        Doctors doctor = new Doctors(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getPassword(), dto.getSpecialProfession(), dto.getAwards(), dto.getBiography());
        doctor.setDoctorStatus(Doctors.DOCTOR_STATUS.REGISTERED);
        doctor.setRemoved(false);
        doctorsRepository.save(doctor);
        String code = UUID.randomUUID().toString();
        doctor.setEmailCode(code + doctor.getUsername());
        doctorsRepository.save(doctor);
        emailService.sendEmail(doctor.getUsername(), "code confirmation: ", (code + doctor.getUsername()));
    }


    @Override
    public Map<String, String> confirmRegister(String code) throws WrongCodeException {
        Doctors doctor = doctorsRepository.findByEmailCode(code);
        WrongCodeException.check(doctor == null, "wrong code");
        doctor.setDoctorStatus(Doctors.DOCTOR_STATUS.ACTIVE);
        doctor.setEmailCode(null);
        doctorsRepository.save(doctor);
        UserDetails userDetails = this.detailsService.loadUserByUsername(doctor.getUsername());
        UserType userType = UserType.getByLabel(userDetails.getAuthorities().iterator().next().getAuthority());
        String accessToken = TokenUtils.generateToken(userDetails, userType, accessTokenSecret, accessTokenExpiration);
        String refreshToken = TokenUtils.generateToken(userDetails, userType, refreshTokenSecret, refreshTokenExpiration);
        Map<String, String> result = new HashMap<>();
        result.put("token", accessToken);
        result.put("refresh", refreshToken);
        ExecutorService service = Executors.newSingleThreadExecutor();

        service.submit(() -> {
            try {

            } catch (Exception e) {
                LOGGER.error("elastic sign up doctor index : ", e.getMessage());
            } finally {
                service.shutdown();
            }
        });


        return result;
    }

    @Override
    public Map<String, String> signIn(String username, String password) throws NotFoundException, UnauthorizedException {
        Doctors doctor = doctorsRepository.findByUsernameAndRemovedFalse(username);
        NotFoundException.check(doctor == null, "no account with given username");
        UnauthorizedException.check(!passwordEncoder.matches(password, doctor.getPassword()), "wrong password");
        Authentication authentication = authenticationManager.authenticate(new AuthToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        doctorsRepository.save(doctor);
        UserDetails userDetails = this.detailsService.loadUserByUsername(doctor.getUsername());
        UserType userType = UserType.getByLabel(userDetails.getAuthorities().iterator().next().getAuthority());
        String accessToken = TokenUtils.generateToken(userDetails, userType, accessTokenSecret, accessTokenExpiration);
        String refreshToken = TokenUtils.generateToken(userDetails, userType, refreshTokenSecret, refreshTokenExpiration);
        Map<String, String> result = new HashMap<>();
        result.put("token", accessToken);
        result.put("refresh", refreshToken);
        return result;
    }


}
