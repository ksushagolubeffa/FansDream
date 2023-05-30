package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomErrorController extends BasicErrorController implements ErrorController, AccessDeniedHandler {

    private static final String ERROR_PATH = "/error";

    public CustomErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes, serverProperties.getError());
    }
//
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }

    @GetMapping(ERROR_PATH)
    public String handleError(HttpServletRequest request) {
//        ErrorAttributeOptions options = ErrorAttributeOptions.of(
//                ErrorAttributeOptions.Include.STACK_TRACE,
//                ErrorAttributeOptions.Include.MESSAGE,
//                ErrorAttributeOptions.Include.BINDING_ERRORS
//        );
//        Map<String, Object> model = getErrorAttributes(request, options);
//        model.put("status", getStatusCode(request));
//
//        switch (getStatusCode(request)) {
//            case 403:
//                model.put("message", "Access denied");
//                return new ModelAndView("admin-dashboard", model);
//            case 404:
//                model.put("message", "Page not found");
//                return new ModelAndView("user-dashboard", model);
//            default:
//                model.put("message", "An error has occurred");
//                return new ModelAndView("default-page", model);
//        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/default";
        } else {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                return "redirect:/admin/dashboard";
            } else if (authorities.contains(new SimpleGrantedAuthority("USER"))) {
                return "redirect:/user/dashboard";
            } else {
                return "redirect:/default";
            }
        }
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            response.sendRedirect("/default");
        } else {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                response.sendRedirect("/admin/dashboard");
            } else if (authorities.contains(new SimpleGrantedAuthority("USER"))) {
                response.sendRedirect("/user/dashboard");
            } else {
                response.sendRedirect("/default");
            }
        }

    }
//
//    private int getStatusCode(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
//    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @RequestMapping("/user/dashboard")
    public String userDashboard() {
        return "user-dashboard.ftlh";
    }

    @RequestMapping("/default")
    public String defaultPage() {
        return "default-page";
    }

}
