package org.example.service;

import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getAllProducts();

    List<ProductComments> getAllComments(Product product);

    void updateProduct(Product product, MultipartFile imageFile) throws IOException;

    Product findProductById(UUID id);

    void addNewProduct(Product product, MultipartFile imageFile) throws IOException;

    boolean deleteProduct(UUID id);

    User getUserByPrincipal(Principal principal);
}
