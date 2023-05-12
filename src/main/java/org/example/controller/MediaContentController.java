package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.service.MediaContentService;
import org.example.service.UserService;
import org.example.service.impl.MediaContentServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MediaContentController {
    private final MediaContentServiceImpl service;

//    @PermitAll
//    @GetMapping("/media")
//    private String getAll(Model model){
//        model.addAttribute("contentList", service.findAllContent());
//        return "media";
//    }

//    @PermitAll
//    @GetMapping("/media")
//    private List<MediaContent> getAll(){
//        return service.findAllContent();
//    }

    @PermitAll
    @GetMapping("/media")
    public ResponseEntity<List<MediaContent>> getAll(){
        List<MediaContent> mediaContents = service.findAllContent();
        return ResponseEntity.ok(mediaContents);
    }

    @PermitAll
    @GetMapping("/media/{content}")
    public ModelAndView mediaInfo(@PathVariable MediaContent content, Model model, Principal principal){
        User user = service.getUserByPrincipal(principal);
        model.addAttribute("isLiked", service.isLiked(content, principal));
        model.addAttribute("currentUser", user);
        model.addAttribute("isAuthorOrAdmin", user.getRole()== User.Role.ADMIN || content.getUser() == user);
        model.addAttribute("comments", service.findAllComments(content));
        model.addAttribute("content", content);
        model.addAttribute("likes", service.getAllLikes(content));
        return new ModelAndView("media-info", model.asMap());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/add")
    public ResponseEntity<String> sendToCheck(Principal principal, MediaContent content, MultipartFile video, MultipartFile image) throws IOException{
        content.setStatus(MediaContent.Status.UNDER_REVIEW);
        service.addMediaContent(principal, content, image, video);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/media/add")
    public ModelAndView addMediaPage(Principal principal, Model model){
        User user = service.getUserByPrincipal(principal);
        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
        return new ModelAndView("media-add", model.asMap());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/media/{content}/edit")
    public ModelAndView editContent(@PathVariable MediaContent content, Principal principal, Model model) {
        User user = service.getUserByPrincipal(principal);
        model.addAttribute("content", content);
        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
        return new ModelAndView("media-edit", model.asMap());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{content}/edit")
    public ResponseEntity<String> updateContent(@PathVariable MediaContent content, MultipartFile image, MultipartFile video) throws IOException {
        service.updateMediaContent(content, image, video);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/media/{content}")
    public ResponseEntity<String> deleteMedia(@PathVariable MediaContent content, Principal principal){
        User user = service.getUserByPrincipal(principal);
        if(!service.deleteMediaContent(user, content.getUuid())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{content}")
    public ResponseEntity<String> deleteMediaContent(@PathVariable MediaContent content, Principal principal){
        User user = service.getUserByPrincipal(principal);
        if(!service.deleteMediaContent(user, content.getUuid())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }


    //has view
//    @PermitAll
//    @GetMapping("/media/{content}")
//    private String mediaInfo(@PathVariable MediaContent content, Model model, Principal principal){
//        User user = service.getUserByPrincipal(principal);
//        model.addAttribute("isLiked", service.isLiked(content, principal));
//        model.addAttribute("currentUser", user);
//        model.addAttribute("isAuthorOrAdmin", user.getRole()== User.Role.ADMIN || content.getUser() == user);
//        model.addAttribute("comments", service.findAllComments(content));
//        model.addAttribute("content", content);
//        model.addAttribute("likes", service.getAllLikes(content));
//        return "media-info";
//    }

    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/media/add")
//    private String sendToCheck(Principal principal, MediaContent content, MultipartFile video, MultipartFile image) throws IOException{
//        content.setStatus(MediaContent.Status.UNDER_REVIEW);
//        service.addMediaContent(principal, content, image, video);
//        return "media";
//    }
//
//    //has view
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/media/add")
//    private String addMediaPage(Principal principal, Model model){
//        User user = service.getUserByPrincipal(principal);
//        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
//        return "media-add";
//    }

    //has view
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/media/{content}/edit")
//    private String editContent(@PathVariable MediaContent content, Principal principal, Model model) {
//        User user = service.getUserByPrincipal(principal);
//        model.addAttribute("content", content);
//        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
//        return "media-edit";
//    }
//
//    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/media/{content}/edit")
//    private String updateContent(@PathVariable MediaContent content, MultipartFile image, MultipartFile video) throws IOException {
//        service.updateMediaContent(content, image, video);
//        return "media";
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/profile/media/{content}")
//    private String deleteMedia(@PathVariable MediaContent content, Principal principal){
//        User user = service.getUserByPrincipal(principal);
//        if(!service.deleteMediaContent(user, content.getUuid())){
//            return "error";
//        }
//        return "media";
//    }

    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/media/{content}")
//    private String deleteMediaContent(@PathVariable MediaContent content, Principal principal){
//        User user = service.getUserByPrincipal(principal);
//        if(!service.deleteMediaContent(user, content.getUuid())){
//            return "error";
//        }
//        return "media";
//    }
}
