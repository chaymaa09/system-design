package com.example.videoservice.services;

import com.example.videoservice.dao.entities.Video;
import com.example.videoservice.dao.repositories.VideoRepository;
import org.springframework.stereotype.Service;

@Service
public class VideoManager {

    private final VideoRepository videoRepository;

    public VideoManager(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video findById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Video not found")
                );
    }

    public Video updateVideo(Video video) {
        return videoRepository.save(video);
    }
}