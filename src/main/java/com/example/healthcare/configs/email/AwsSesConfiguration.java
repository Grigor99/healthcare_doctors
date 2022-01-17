package com.example.healthcare.configs.email;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSesConfiguration {

    private final String region;

    public AwsSesConfiguration(@Value("${email.region}") String region) {
        this.region = region;
    }

    /**
     * Build the AWS ses client
     *
     * @return AmazonSimpleEmailServiceClientBuilder
     */
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider = new ClasspathPropertiesFileCredentialsProvider("AwsCredentials.properties");

        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(propertiesFileCredentialsProvider)
                .withRegion(region).build();
    }
}
