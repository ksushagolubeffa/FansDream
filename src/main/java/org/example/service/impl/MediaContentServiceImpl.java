package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.repository.MediaCommentsRepository;
import org.example.repository.MediaContentRepository;
import org.example.repository.UserRepository;
import org.example.service.MediaContentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MediaContentServiceImpl implements MediaContentService {

    private final MediaContentRepository repository;
    private final UserRepository userRepository;
    private final MediaCommentsRepository commentsRepository;

    @Override
    public List<MediaContent> findAllContent() {
        return repository.findAll().stream().filter(content -> content.getStatus()== MediaContent.Status.ACCEPTED).collect(Collectors.toList());
    }

    @Override
    public List<MediaComments> findAllComments(MediaContent content) {
        List<MediaComments> commentList = new ArrayList<>();
        commentsRepository.findAll().forEach(comments -> {
            if(comments.getMediaID().equals(content.getUuid())){
                commentList.add(comments);
            }
        });
        return commentList;
    }

    @Override
    public Integer getAllLikes(MediaContent content) {
        return content.getLikes().size();
    }

    @Override
    public boolean isLiked(MediaContent content, Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName());
        if(content.getLikes().stream().filter(like->like.getUser()==user).count() == 1){
            return true;
        }
        return false;
    }

    @Override
    public MediaContent findContentById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateMediaContent(MediaContent content, MultipartFile imageFile, MultipartFile videoFile) throws IOException {
        MediaContent content1 = repository.findById(content.getUuid()).orElse(null);
        if(content1!=null){
            byte[] imageBytes = imageFile.getBytes();
            content.setPreviewImage(imageBytes);
            byte[] videoBytes = videoFile.getBytes();
            content.setVideo(videoBytes);
            content.setStatus(MediaContent.Status.UNDER_REVIEW);
            repository.delete(content1);
            repository.save(content);
        } else{
            log.error("not such content1 in database");
        }
    }

    @Override
    public void addMediaContent(Principal principal, MediaContent content, MultipartFile imageFile, MultipartFile videoFile) throws IOException {
        User user = getUserByPrincipal(principal);
        if(user.getRole() == User.Role.ADMIN){
            content.setStatus(MediaContent.Status.ACCEPTED);
        }
        content.setUser(user);
        byte[] imageBytes = imageFile.getBytes();
        byte[] videoBytes = videoFile.getBytes();
        content.setVideo(videoBytes);
        content.setPreviewImage(imageBytes);
        repository.save(content);
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByUsername(principal.getName());
    }

    @Override
    public boolean deleteMediaContent(User user, UUID id) {
        MediaContent content = repository.findById(id).orElse(null);
        if(content!=null){
            if(content.getUser().getUuid().equals(user.getUuid())){
                repository.delete(content);
                return true;
            }
            log.error("Media content with id = {} is not found", id);

        } else{
            log.error("not such content in database");
        }
        return false;
    }
}
