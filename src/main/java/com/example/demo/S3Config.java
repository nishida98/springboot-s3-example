package com.example.demo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    public BasicAWSCredentials crenditials() {
        return new BasicAWSCredentials("AKIARILDOGGDAGT5QUVX", "RXKOj8il3rL5DFdlqOxY4UNvp07gOOhov6hiIpXZ");
    }

    @Bean
    public AmazonS3 s3Client() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(crenditials()))
                .withRegion(Regions.SA_EAST_1)
                .build();
    }

}
