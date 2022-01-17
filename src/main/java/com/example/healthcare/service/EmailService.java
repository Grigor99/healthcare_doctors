package com.example.healthcare.service;


import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private AmazonSimpleEmailService client;
    @Value("${email.sender}")
    private String source;
    @Value("${aws.ses.region}")
    private String region;

    public EmailService() {
        client =
                AmazonSimpleEmailServiceClientBuilder.standard()
                        .withCredentials(new  ClasspathPropertiesFileCredentialsProvider("AwsCredentials.properties"))
                        .withRegion(Regions.US_EAST_1).build();
    }

    public void sendEmail(String to, String subject, String content) {

        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(content)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(subject)))
                    .withSource(source);
            client.sendEmail(request);
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }

}