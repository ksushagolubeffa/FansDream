package org.example.service;

import org.example.entity.Order;
import org.example.entity.OrderDTO;
import org.example.entity.Product;
import org.example.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    List<Order> getAllOrders();

    Product findProductByOrder(Order order);

    Order findOrderById(UUID id);

    List<Product> findProductsById(List<Order> orders);

    User getUserByPrincipal(Principal principal);

    boolean endOrder(List<Order> order);

    void addNewOrder(Order order);

    boolean deleteOrder(UUID id);
}
