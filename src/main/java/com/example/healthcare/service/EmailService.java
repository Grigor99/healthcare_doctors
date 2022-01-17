package com.example.healthcare.service;


import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.example.healthcare.util.exceptionhandler.ExceptionHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    private static final String CHAR_SET = "UTF-8";
    private static final String SUBJECT = "Test from our application";
    private final AmazonSimpleEmailService service;
    private final String sender;

    @Autowired
    public EmailService(AmazonSimpleEmailService service,
                        @Value("${email.sender}") String sender) {
        this.service = service;
        this.sender = sender;
    }

    public void sendEmail(String email, String body) {
        try {
            int requestTimeout = 3000;
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(email))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset(CHAR_SET).withData(body)))
                            .withSubject(new Content()
                                    .withCharset(CHAR_SET).withData(SUBJECT)))
                    .withSource(sender).withSdkRequestTimeout(requestTimeout);
            service.sendEmail(request);
        } catch (RuntimeException e) {
            LOGGER.error("Error occurred sending email to {} ", email, e);
        }
    }
}