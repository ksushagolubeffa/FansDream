package org.example.service;

import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.Notification;

import java.security.Principal;
import java.util.List;

public interface NotificationService {

    void addNotification(Principal principal, MediaContent content, MediaComments comment);

    void addContentNotification(MediaContent content);

    List<Notification> getAll(Principal principal);

    void deleteNotification(Notification notification);

    void deleteAll(Principal principal);
}
