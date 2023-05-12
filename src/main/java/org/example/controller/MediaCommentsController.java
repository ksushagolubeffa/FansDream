package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.service.MediaCommentsService;
import org.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MediaCommentsController {

    private final MediaCommentsService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{content}/addComment")
    public ResponseEntity<String> addComment(@PathVariable MediaContent content, MediaComments comment, Principal principal){
        service.save(principal, content, comment);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{content}/deleteComment")
    public ResponseEntity<String> deleteComment(@PathVariable MediaContent content, MediaComments comments){
        if(!service.delete(content, comments.getUuid())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/media/{content}/addComment")
//    private String addComment(@PathVariable MediaContent content, MediaComments comment, Principal principal){
//        service.save(principal, content, comment);
//        return "redirect:/media/{content}";
//    }
//
//    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/media/{content}/deleteComment")
//    private String deleteComment(@PathVariable MediaContent content, MediaComments comments){
//        if(!service.delete(content, comments.getUuid())){
//            return "error";
//        }
//        return "redirect:/media/{content}";
//    }

}
