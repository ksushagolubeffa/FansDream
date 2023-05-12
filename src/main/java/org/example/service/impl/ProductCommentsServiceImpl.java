package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.example.repository.ProductCommentsRepository;
import org.example.repository.ProductRepository;
import org.example.service.ProductCommentsService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCommentsServiceImpl implements ProductCommentsService {

    private final ProductCommentsRepository repository;
    private final ProductRepository productRepository;

    @Override
    public void saveComment(User user, Product product, ProductComments comment) {
        comment.setUsername(user.getUsername());
        comment.setProduct(product);
        repository.save(comment);
    }

    @Override
    public boolean delete(Product product, UUID id) {
        ProductComments comment = repository.findById(id).orElse(null);
        if(comment!=null){
            if(comment.getProduct().getUuid().equals(product.getUuid())){
                repository.delete(comment);
                return true;
            }
            log.error("Comment with id = {} is not found", id);
        } else{
            log.error("not such comment in database");
        }
        return false;
    }
}
