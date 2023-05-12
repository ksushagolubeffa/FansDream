package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.Notification;
import org.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/notifications")
    private List<Notification> getAll(Principal principal){
        return service.getAll(principal);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/notifications/{notification}")
    public ResponseEntity<String> deleteNotification(@PathVariable Notification notification){
        service.deleteNotification(notification);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/notifications")
    public ModelAndView deleteAllNotifications(Principal principal){
        service.deleteAll(principal);
        return new ModelAndView("profile");
    }

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/profile/notifications")
//    private String getAllNotifications(Model model, Principal principal){
//        model.addAttribute("notificationList", service.getAll(principal));
//        return "notification";
//    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/profile/notifications/{notification}")
//    private String deleteNotification(@PathVariable Notification notification){
//        service.deleteNotification(notification);
//        return "redirect:/";
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/profile/notifications")
//    private String deleteAllNotifications(Principal principal){
//        service.deleteAll(principal);
//        return "profile";
//    }
}
