package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.entity.UserForm;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final UserService service;

    //has view
//    @PreAuthorize("isAnonymous()")
//    @PostMapping("/signUp")
//    public String createUser(User user, MultipartFile image) throws IOException {
//        if(!service.saveUser(user, image)){
//        }
//        return "profile";
//    }
//
//    //has view
//    @PreAuthorize("isAnonymous()")
//    @GetMapping("/signUp")
//    public String signUpUser(){
//        return "sign-up";
//    }
//
////    has view
//    @PermitAll
//    @PostMapping("/signIn")
//    public String loginUser(UserForm form){
//        if(!service.signInUser(form)){
//            return "error";
//        }
//        return "profile";
//    }
//
//    //has view
//    @PreAuthorize("isAnonymous()")
//    @GetMapping("/signIn")
//    public String signInUser(){
//        return "sign-in";
//    }
    @PreAuthorize("isAnonymous()")
    @PostMapping("/signUp")
    @ResponseBody
    public ResponseEntity<String> createUser(@RequestBody User user, @RequestParam("image") MultipartFile image) throws IOException {
        if(service.saveUser(user, image)){
            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to create user");
        }
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signUp")
    @ResponseBody
    public ModelAndView signUpUser(){
        ModelAndView modelAndView = new ModelAndView("sign-up");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signIn")
    @ResponseBody
    public ResponseEntity<String> loginUser(@RequestBody UserForm form){
        if(service.signInUser(form)){
            return ResponseEntity.ok("User logged in successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to log in user");
        }
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signIn")
    @ResponseBody
    public ModelAndView signInUser(){
        ModelAndView modelAndView = new ModelAndView("sign-in");
        modelAndView.addObject("userForm", new UserForm());
        return modelAndView;
    }

}
