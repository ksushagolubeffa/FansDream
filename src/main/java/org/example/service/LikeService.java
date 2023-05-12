package org.example.service;

import org.example.entity.MediaContent;

import java.security.Principal;
import java.util.List;

public interface LikeService {

    void addLike(Principal principal, MediaContent content);

    List<MediaContent> findAllUserLikes(Principal principal);

    void removeLike(Principal principal, MediaContent content);
}
