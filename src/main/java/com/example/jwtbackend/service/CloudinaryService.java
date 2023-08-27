package com.example.jwtbackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "cloudinary.cloud_name",
                "api_key", "cloudinary.api_key",
                "api_secret", "cloudinary.api_secret"
        ));
    }
    public boolean deleteImage(String imageLink) {
        String pathWithoutProtocolAndDomain = imageLink.replaceFirst("^(http(s)?://)?[^/]+/", "");
        String publicId = imageLink.replaceAll("\\.(jpg|png|gif|jpeg)$", "");
        System.out.println(publicId);
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("resource_type","image"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
