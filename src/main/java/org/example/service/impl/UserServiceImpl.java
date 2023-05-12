package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.*;
import org.example.repository.MediaContentRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final MediaContentRepository contentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean deleteUser(UUID userId) {
        if (repository.findById(userId).isPresent()) {
            repository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User findUserByUsername(Principal principal) {
        return repository.findUserByUsername(principal.getName());
    }

    @Override
    public boolean saveUser(User user, MultipartFile imageFile) throws IOException {
        if (repository.findUserByUsername(user.getUsername()) != null) {
            return false;
        }
        User newUser = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(User.Role.USER)
                .balance(0)
                .state(User.State.ACTIVE)
                .build();
        if(imageFile!=null){
            newUser.setImage(imageFile.getBytes());
        }
        repository.save(newUser);
        return true;
    }

    @Override
    public void updateUser(User user, MultipartFile imageFile) throws IOException {
        User user1 = repository.findById(user.getUuid()).orElse(null);
        if(user1!=null){
            if (imageFile != null) {
                byte[] imageBytes = imageFile.getBytes();
                user.setImage(imageBytes);
            }else{
                user.setImage(user1.getImage());
            }
            repository.delete(user1);
            repository.save(user);
        } else{
//            log.error("not such user1 in database");
        }
    }

    @Override
    public User findUserById(UUID userId) {
        Optional<User> userFromDb = repository.findById(userId);
        return userFromDb.orElse(null);
    }


    @Override
    public List<MediaContent> getContentForCheck() {
        return contentRepository.findAll().stream().filter(content -> content.getStatus()== MediaContent.Status.UNDER_REVIEW).collect(Collectors.toList());
    }

    @Override
    public List<Order> findAllOrders(Principal principal) {
        User user = repository.findUserByUsername(principal.getName());
        return user.getOrders();
    }

    @Override
    public boolean signInUser(UserForm form){
        User user = repository.signInUser(form.getUsername(), form.getPassword());
        if(user!= null){
            return true;
        }
        return false;
    }

    @Override
    public List<MediaContent> findAllLikes(Principal principal) {
        User user = repository.findUserByUsername(principal.getName());
        List <MediaContent> list = new ArrayList<>();
        user.getLikes().forEach(like->{
            list.add(like.getContent());
        });
        return list;
    }

//    @Override
//    public void updateUsername(String username, UUID id) {
//        repository.updateUsername(username, id);
//    }
//
//    @Override
//    public void updatePassword(String password, UUID id) {
//        repository.updatePassword(password, id);
//    }
}
