package org.example.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService service;

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/cart/select/{id}")
//    public String selectOrders(@PathVariable("id") UUID id) {
//        Order order = service.findById(id);
//        order.setSelected(true);
//        return"redirect:/cart";
//    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart/select")
    public void selectOrders(@RequestBody List<UUID> ids) {
        List<Order> orders = service.getAll();
        for (Order order : orders) {
            order.setSelected(ids.contains(order.getUuid()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart/delete")
    public void deleteSelectedOrders() {
        service.getAll().forEach(order -> {
            if(order.isSelected()){
                service.deleteOrder(order.getUuid());
            }
        });
//        orders.removeIf(Order::isSelected);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart/buy")
    public void buySelectedOrders() {
        List<Order> orders = service.getAll();
        service.endOrders(orders);
    }
}
