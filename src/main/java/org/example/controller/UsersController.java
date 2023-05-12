package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.service.NotificationService;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.example.service.impl.NotificationServiceImpl;
import org.example.service.impl.ProductServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private final UserService service;
    private final NotificationServiceImpl notificationService;
    private final ProductServiceImpl productService;

    //has view
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    //has view
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users/{user}")
    private String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        return "user-info";
    }


    //TO-DO
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/users")
    private String banUser(@PathVariable("user") User user) {
        service.findUserById(user.getUuid()).setState(User.State.NOT_ACTIVE);
        return "redirect:/";
    }


    //has view
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    private String getProfilePage(Principal principal, Model model) {
        User user = service.findUserByUsername(principal);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/media")
    private String getMyMedia(Model model, Principal principal) {
        User user = service.findUserByUsername(principal);
        model.addAttribute("contentList", user.getContentList());
        return "media";
    }

    //has view
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/edit")
    private String updateProfile(User user, MultipartFile image) throws IOException {
        service.updateUser(user, image);
        return "redirect:/profile";
    }

    //has view
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/edit")
    private String editProfile(Principal principal, Model model) {
        User user = service.findUserByUsername(principal);
        model.addAttribute("user", user);
        return "profile-edit";
    }

    //has view
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    private String deleteUser(User user, Model model) {
        if (!service.deleteUser(user.getUuid())) {
            return "error";
        }
        return "redirect:/sign-up";
    }

    @PermitAll
    @GetMapping("/users/{user}/media")
    private String allMedia(@PathVariable User user, Model model) {
        model.addAttribute("contentList", user.getContentList());
        return "media";
    }

    //has view
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/orders")
    private String getOrders(Principal principal, Model model){
        List<Order> orderList = service.findAllOrders(principal);
        List<Product> list = orderList.stream().map(order -> productService.findProductById(order.getProductID())).collect(Collectors.toList());
        model.addAttribute("products", list);
        return "orders";
    }

    //has view
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/profile/check")
    private String getContentPage(Model model){
        model.addAttribute("contentList", service.getContentForCheck());
        return "check";
    }

    //has view
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/profile/check/{content}/add")
    private String addContent(@PathVariable MediaContent content){
        content.setStatus(MediaContent.Status.ACCEPTED);
        notificationService.addContentNotification(content);
        return "redirect:/";
    }

    //has view
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/profile/check/{content}/remove")
    private String removeContent(@PathVariable MediaContent content){
        notificationService.addContentNotification(content);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/likes")
    private String getAllLikes(Model model, Principal principal){
        model.addAttribute("contentList", service.findAllLikes(principal));
        return "media";
    }
}
