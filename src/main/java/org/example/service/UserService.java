package org.example.service;

import org.example.entity.*;
import org.example.entity.form.UserForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface UserService {

    void deleteUser(Principal principal);

    boolean arePasswordsEquals(Principal principal, String password);

    boolean saveUser(String username, String email, String password) throws IOException;

    void updateUser(Principal principal, String username, String password, MultipartFile imageFile) throws IOException;

    User findUserById(UUID userId);

    List <MediaContent> getContentForCheck();

    void signInUser(UserForm form);

    List<MediaContent> findAllLikes(Principal principal);

    List<Product> findAllOrders(Principal principal);

    List<User> getAllUsers();

    User findUserByUsername(Principal principal);

    User findUserByEmail(Principal principal);

//    void updateUsername(String username, UUID id);
//
//    void updatePassword(String password, UUID id);


}
