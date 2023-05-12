package org.example.service;

import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface ProductCommentsService {

    void saveComment(User user, Product product, ProductComments comment);

    boolean delete(Product product, UUID id);

}
