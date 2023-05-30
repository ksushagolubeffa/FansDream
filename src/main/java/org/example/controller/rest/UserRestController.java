package org.example.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.entity.response.MediaContentResponse;
import org.example.entity.response.UserResponse;
import org.example.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class UserRestController {

    private final UserService service;
//    private JSONObject response = null;

    public UserRestController(@Qualifier("userServiceBase") UserService userService) {
        this.service = userService;
    }

    //all works correct
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/users")
//    public ResponseEntity<List<UserResponse>> getAllUsers() {
//        JSONObject response = null;
//        List<User> users = service.getAllUsers();
//        List<UserResponse> userlist = new ArrayList<>();
//
//        users.forEach(user->
//            userlist.add(new UserResponse(user.getUuid(),
//                    user.getUsername(),
//                    user.getEmail(),
//                    user.getRole(),
//                    user.getBalance(),
//                    user.getState()))
//        );
//        response.
//        assert false;
//        if (response != null) {
//            response.accumulate("userlist", userlist);
//        }
//        return ResponseEntity.ok(userlist);
//    }
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/users")
//    public ResponseEntity<?> getAllUsers() {
//        List<User> userList = service.getAllUsers();
//        List<UserResponse> users = new ArrayList<>();
//
//        userList.forEach(user->
//            users.add(new UserResponse(user.getUuid(),
//                    user.getUsername(),
//                    user.getEmail(),
//                    user.getRole(),
//                    user.getBalance(),
//                    user.getState()))
//        );
//        return ResponseEntity.ok(users);
//    }

//    @GetMapping("/users")
//    public List<UserResponse> getAllUsers() {
//        List<User> userList = service.getAllUsers();
//        List<UserResponse> users = new ArrayList<>();
//
//        userList.forEach(user->
//                users.add(new UserResponse(user.getUuid(),
//                        user.getUsername(),
//                        user.getEmail(),
//                        user.getRole(),
//                        user.getBalance(),
//                        user.getState()))
//        );
//        return users;
//    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/media")
    public List<MediaContentResponse> getMyMedia(Principal principal) {
        User user = service.findUserByEmail(principal);
        List<MediaContent> contentList = user.getContentList();
        List<MediaContentResponse> list = new ArrayList<>();
        contentList.forEach(content -> {
            list.add(new MediaContentResponse(
                    content.getUuid(),
                    content.getName(),
                    content.getDescription(),
                    content.getStatus()
            ));
        });
        return list;
    }

    //all works correct
    @PermitAll
    @GetMapping("/users/{id}/media")
    public List <MediaContentResponse> allMedia(@PathVariable("id") UUID id) {
        User user = service.findUserById(id);
        List<MediaContent> contentList = user.getContentList().stream().filter(content -> content.getStatus() == MediaContent.Status.ACCEPTED).toList();
        List<MediaContentResponse> list = new ArrayList<>();
        contentList.forEach(content -> {
            list.add(new MediaContentResponse(
                    content.getUuid(),
                    content.getName(),
                    content.getDescription()
            ));
        });
        return list;
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/likes")
    public List<MediaContentResponse> getAllLikes(Principal principal){
        List<MediaContent> contentList = service.findAllLikes(principal);
        List<MediaContentResponse> list = new ArrayList<>();
        contentList.forEach(content -> {
            list.add(new MediaContentResponse(
                    content.getUuid(),
                    content.getName(),
                    content.getDescription()
            ));
        });
        return list;
    }

    @GetMapping("/users/{id}/image")
    public ResponseEntity<?> showImage(@PathVariable("id") UUID id) throws IOException {
        User user = service.findUserById(id);
        return ResponseEntity.ok()
                .header("fileName", user.getUsername())
                .contentType(MediaType.valueOf(user.getImage().getContentType()))
                .contentLength(user.getImage().getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(user.getImage().getBytes())));
    }
}
