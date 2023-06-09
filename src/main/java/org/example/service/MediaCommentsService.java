package org.example.service;

import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.User;

import java.security.Principal;
import java.util.UUID;

public interface MediaCommentsService {

    void save(Principal principal, UUID id, String comment);

    boolean delete(UUID id, UUID idComment);

    User getUserByPrincipal(Principal principal);
}
