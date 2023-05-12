package org.example.repository;

import org.example.entity.MediaContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaContentRepository extends JpaRepository<MediaContent, UUID> {
    MediaContent findMediaContentByName(String name);
}
