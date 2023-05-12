package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.example.repository.ProductCommentsRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCommentsRepository commentsRepository;
    private final UserRepository userRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductComments> getAllComments(Product product) {
        List<ProductComments> commentList = new ArrayList<>();
        commentsRepository.findAll().forEach(comments -> {
            if(comments.getProductID().equals(product.getUuid())){
                commentList.add(comments);
            }
        });
        return commentList;
    }

    @Override
    public void updateProduct(Product product, MultipartFile imageFile) throws IOException {
        Product prod1 = productRepository.findById(product.getUuid()).orElse(null);
        if(prod1!=null){
            if (imageFile != null) {
                byte[] imageBytes = imageFile.getBytes();
                product.setImage(imageBytes);
            }else{
                product.setImage(prod1.getImage());
            }
            productRepository.delete(prod1);
            productRepository.save(product);
        } else{
            log.error("not such prod1 in database");
        }
    }

    @Override
    public Product findProductById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void addNewProduct(Product product, MultipartFile imageFile) throws IOException {
        byte[] imageBytes = imageFile.getBytes();
        product.setImage(imageBytes);
        productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product!=null){
            productRepository.delete(product);
            return true;
        } else{
            log.error("not such product in database");
            return false;
        }

    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByUsername(principal.getName());
    }
}
