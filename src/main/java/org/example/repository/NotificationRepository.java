package org.example.repository;

import org.example.entity.Notification;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    @Modifying
    @Query("delete from Notification n where n.user=:user")
    void deleteNotificationsByUser(User user);

    @Query("select n from Notification n where  n.user =: user")
    List<Notification> getAllByUser(User user);
}
