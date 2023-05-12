package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.Notification;
import org.example.entity.User;
import org.example.repository.MediaContentRepository;
import org.example.repository.NotificationRepository;
import org.example.repository.UserRepository;
import org.example.service.NotificationService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final UserRepository userRepository;
    private final MediaContentRepository contentRepository;


    @Override
    public void addNotification(Principal principal, MediaContent content, MediaComments comment) {
        User user = userRepository.findUserByUsername(principal.getName());
        Notification notification = new Notification(user, content.getUuid(), comment.getUuid());
        repository.save(notification);
    }

    @Override
    public void addContentNotification(MediaContent content) {
        Notification notification;
        if(content.getStatus() == MediaContent.Status.ACCEPTED){
            notification = new Notification(content.getUser(), content.getUuid(), Notification.Message.ACCEPTED);
        } else{
            content.setStatus(MediaContent.Status.REJECTED);
            notification = new Notification(content.getUser(), content.getUuid(), Notification.Message.REJECTED);
        }
        repository.save(notification);
    }

    @Override
    public List<Notification> getAll(Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName());
        return repository.getAllByUser(user);
    }

    @Override
    public void deleteNotification(Notification notification) {
        repository.delete(notification);
    }

    @Override
    public void deleteAll(Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName());
        repository.deleteNotificationsByUser(user);
    }
}
