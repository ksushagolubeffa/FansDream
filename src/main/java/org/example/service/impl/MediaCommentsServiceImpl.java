package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.repository.MediaCommentsRepository;
import org.example.repository.MediaContentRepository;
import org.example.repository.NotificationRepository;
import org.example.repository.UserRepository;
import org.example.service.MediaCommentsService;
import org.example.service.NotificationService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MediaCommentsServiceImpl implements MediaCommentsService {

    private final MediaCommentsRepository repository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Override
    public void save(Principal principal, MediaContent content, MediaComments comment) {
        User user = userRepository.findUserByUsername(principal.getName());
        comment.setUsername(user.getUsername());
        comment.setContent(content);
        repository.save(comment);
        notificationService.addNotification(principal, content, comment);
    }

    @Override
    public boolean delete(MediaContent content, UUID id) {
        MediaComments comment = repository.findById(id).orElse(null);
        if(comment!=null){
            if(comment.getContent().getUuid().equals(content.getUuid())){
                repository.delete(comment);
                return true;
            }
            log.error("Comment with id = {} is not found", id);
        } else{
            log.error("not such comment in database");
        }
        return false;
    }
}
