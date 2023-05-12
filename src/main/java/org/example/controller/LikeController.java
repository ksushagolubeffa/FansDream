package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.service.LikeService;
import org.example.service.MediaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.PermitAll;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LikeController {

    @Autowired
    private final LikeService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{content}/removeLike")
    public ResponseEntity<String> deleteLike(@PathVariable MediaContent content, Principal principal){
        service.removeLike(principal, content);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{content}/addLike")
    public ResponseEntity<String> addLike(@PathVariable MediaContent content, Principal principal){
        service.addLike(principal, content);
        return ResponseEntity.ok().build();
    }

    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/media/{content}/removeLike")
//    private String deleteLike(@PathVariable MediaContent content, Principal principal){
//        service.removeLike(principal, content);
//        return "redirect:/media/{content}";
//    }
//
//    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/media/{content}/addLike")
//    private String addLike(@PathVariable MediaContent content, Principal principal){
//        service.addLike(principal, content);
//        return "redirect:/media/{content}";
//    }
}
