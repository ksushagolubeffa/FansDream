package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Like;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.repository.LikeRepository;
import org.example.repository.UserRepository;
import org.example.service.LikeService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final UserRepository userRepository;


    @Override
    public void addLike(Principal principal, MediaContent content) {
        User user = userRepository.findUserByUsername(principal.getName());
        Like like = new Like(user, content);
        user.getLikes().add(like);
        content.getLikes().add(like);
        repository.save(like);
    }

    @Override
    public List<MediaContent> findAllUserLikes(Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName());
        repository.findAllLikesByUser(user);
        return null;
    }


    @Override
    public void removeLike(Principal principal, MediaContent content) {
        Like like = repository.findByUserAndContent(userRepository.findUserByUsername(principal.getName()), content);
        if(like!=null){
            repository.delete(like);
        }
    }
}
