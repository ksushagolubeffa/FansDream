package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.service.OrderService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Product findProductByOrder(Order order){
        return (Product) productRepository.findAll().stream().filter(product -> product.getUuid().equals(order.getProductID()));
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByUsername(principal.getName());
    }

    @Override
    public List<Product> findProductsById(List<Order> orders){
        return orders.stream()
                .map(order -> productRepository.findById(order.getProductID()))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }


    @Override
    public Order findOrderById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean endOrder(List<Order> orders) {
        User user = userRepository.findById(orders.get(0).getUserID()).orElse(null);
        if(user!=null){
            int money = user.getBalance();
            List<Product> products = new ArrayList<>();
            orders.forEach(order -> {
                products.add(productRepository.findById(order.getProductID()).orElse(null));
            });
            int sum = products.stream().filter(Objects::nonNull).mapToInt(Product::getPrice).sum();
            if(money>=sum){
                user.setBalance(money - sum);
                orders.forEach(order -> {
                    order.setExecuted(true);
                    user.getOrders().add(order);
                });
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void addNewOrder(Order order) {
        order.setExecuted(false);
        repository.save(order);
    }

    @Override
    public boolean deleteOrder(UUID id) {
        Order order = repository.findById(id).orElse(null);
        if(order!=null){
            repository.delete(order);
            return true;
        } else{
            log.error("not such order in database");
            return false;
        }
    }
}
