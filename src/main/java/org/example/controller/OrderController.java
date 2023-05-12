package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    //has view
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    private String getAllOrders(User user, Model model){
        List<Order> orderList = new ArrayList<>();
        service.getAllOrders().forEach(order -> {
            if(order.getUserID().equals(user.getUuid()) && !order.isExecuted()){
                orderList.add(order);
            }
        });
        List<Product> products = service.findProductsById(orderList);
        int sum = products.stream().mapToInt(Product::getPrice).sum();
        model.addAttribute("products", products);
        model.addAttribute("total", sum);
        return "cart";
    }

    //has view
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart")
    private String endOrder(List<Order> orders){
        if(!service.endOrder(orders)){
            return "error";
        }
        return "profile";
    }

    //has view
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/products/{product}/addToCart")
    private String addToCart(@PathVariable Product product, Principal principal){
        User user = service.getUserByPrincipal(principal);
        service.addNewOrder(new Order(user.getUuid(), product.getUuid(), false));
        return "redirect:/";
    }

    //has view
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart/{order}")
    private String deleteOrder(@PathVariable Order order){
        if(!service.deleteOrder(order.getUuid())){
            return "error";
        }
        return "redirect:/";
    }
}
