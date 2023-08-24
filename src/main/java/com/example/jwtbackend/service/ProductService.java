package com.example.jwtbackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.jwtbackend.entites.Product;
import com.example.jwtbackend.entites.User;
import com.example.jwtbackend.repository.ProductRepository;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;


    public String uploadImage(MultipartFile file) throws IOException {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", dotenv.get("cloudinary.cloud_name"),
                "api_key", dotenv.get("cloudinary.api_key"),
                "api_secret", dotenv.get("cloudinary.api_secret")
        ));

        Map<String, String> options = ObjectUtils.asMap("resource_type", "auto");
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), options);

        return uploadResult.get("secure_url").toString();
    }
    public List<String> getAllImages(){
        return productRepository.getAllByImageUrl();
    }
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProductsByUser(Long id) {
        return productRepository.getByUserId(id);
    }
    public long getTotalProductCount() {
        return productRepository.count();
    }
}
