package com.studentmisportal.backend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinaryName}")
    private String cloudinaryName;

    @Value("${cloudApiKey}")
    private String cloudApiKey;

    @Value("${cloudSecretKey}")
    private String cloudSecretKey;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryName,
                "api_key", cloudApiKey,
                "api_secret", cloudSecretKey
        ));
    }
}
