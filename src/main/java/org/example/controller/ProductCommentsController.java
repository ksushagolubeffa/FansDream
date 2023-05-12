package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.example.service.ProductCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ProductCommentsController {
    @Autowired
    private final ProductCommentsService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/products/{product}/addComment")
    @ResponseBody
    public ResponseEntity<String> addComment(@PathVariable("product") Product product, @RequestBody ProductComments comment, @AuthenticationPrincipal User user){
        service.saveComment(user, product, comment);
        return ResponseEntity.ok("Comment added successfully");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/products/{product}/deleteComment")
    @ResponseBody
    public ResponseEntity<String> deleteComment(@PathVariable("product") Product product, @RequestBody ProductComments comment){
        if(service.delete(product, comment.getUuid())){
            return ResponseEntity.ok("Comment deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete comment");
        }
    }

    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/products/{product}/addComment")
//    private String addComment(@PathVariable Product product, ProductComments comment, User user){
//        service.saveComment(user, product, comment);
//        return "redirect:/";
//    }
//
//    //has view
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/products/{product}/deleteComment")
//    private String deleteComment(@PathVariable Product product, ProductComments comments){
//        if(!service.delete(product, comments.getUuid())){
//            return "error";
//        }
//        return "redirect:/";
//    }
}
