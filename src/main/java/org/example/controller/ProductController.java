package org.example.controller;

import org.example.entity.Product;
import org.example.entity.User;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PermitAll
    @GetMapping("/products")
    private List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //    @PermitAll
//    @GetMapping("/products")
//    private String getAllProducts(Model model){
//        model.addAttribute("products", productService.getAllProducts());
//        return "products";
//    }

    @PermitAll
    @GetMapping("/products/{product}")
    public ModelAndView getProduct(@PathVariable("product") Product product, Principal principal) {
        User user = productService.getUserByPrincipal(principal);
        ModelAndView modelAndView = new ModelAndView("product-info");
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("comments", productService.getAllComments(product));
        modelAndView.addObject("product", productService.findProductById(product.getUuid()));
        modelAndView.addObject("isAdmin", user.getRole() == User.Role.ADMIN);
        return modelAndView;
    }


    //    //has view
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/products/{product}")
//    private String getProduct(@PathVariable("product") Product product, Model model, Principal principal){
//        User user = productService.getUserByPrincipal(principal);
//        model.addAttribute("currentUser", user);
//        model.addAttribute("comments", productService.getAllComments(product));
//        model.addAttribute("product", productService.findProductById(product.getUuid()));
//        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
//        return "product-info";
//    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/products/add")
    @ResponseBody
    private ResponseEntity<String> addProduct(@RequestBody Product product, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        productService.addNewProduct(product, imageFile);
        return ResponseEntity.ok("Product added successfully");
    }

//    //has view
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/products/add")
//    private String addProduct(Product product, MultipartFile imageFile) throws IOException {
//        productService.addNewProduct(product, imageFile);
//        return "products";
//    }

    @GetMapping("/products/add")
    @ResponseBody
    private ModelAndView addProductPage(){
        ModelAndView modelAndView = new ModelAndView("product-add");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    //has view
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/products/add")
//    private String addProductPage(){
//        return "product-add";
//    }

    @PostMapping("/products/edit/{id}")
    @ResponseBody
    private ResponseEntity<String> updateProduct(@PathVariable("id") UUID id, @RequestBody Product product, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        productService.updateProduct(product, imageFile);
        return ResponseEntity.ok("Product updated successfully");
    }

    //has view
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/products/edit/{product}")
//    private String updateProduct(@PathVariable Product, MultipartFile imageFile) throws IOException {
//        productService.updateProduct(product, imageFile);
//        return "redirect:/products";
//    }

    @GetMapping("/products/edit/{id}")
    @ResponseBody
    private ModelAndView editProduct(@PathVariable("id") UUID id){
        ModelAndView modelAndView = new ModelAndView("product-edit");
        modelAndView.addObject("product", productService.findProductById(id));
        return modelAndView;
    }

    //has view
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/products/edit/{product}")
//    private String editProduct(@PathVariable Product product, Model model){
//        model.addAttribute("product", product);
//        return "product-edit";
//    }

    @PostMapping("/products/delete/{id}")
    @ResponseBody
    private ResponseEntity<String> deleteProduct(@PathVariable("id") UUID id){
        if(!productService.deleteProduct(id)){
            return ResponseEntity.badRequest().body("Error deleting product");
        }
        return ResponseEntity.ok("Product deleted successfully");
    }

    //has view
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/products/{product}")
//    private String deleteProduct(@PathVariable Product product, Model model){
//        if(!productService.deleteProduct(product.getUuid())){
//            model.addAttribute("errorMessage", "Произошла ошибка при удалении товара, попробуйте снова");
//            return "products";
//        }
//        return "redirect:/products";
//    }
}
