package org.example.service;

import org.example.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface UserService {

    boolean deleteUser(UUID id);

    boolean saveUser(User user, MultipartFile imageFile) throws IOException;

    void updateUser(User user, MultipartFile imageFile) throws IOException;

    User findUserById(UUID userId);

    List <MediaContent> getContentForCheck();

    boolean signInUser(UserForm form);

    List<MediaContent> findAllLikes(Principal principal);

    List<Order> findAllOrders(Principal principal);

    List<User> getAllUsers();

    User findUserByUsername(Principal principal);

//    void updateUsername(String username, UUID id);
//
//    void updatePassword(String password, UUID id);


}
