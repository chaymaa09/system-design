package com.example.videoservice.web;

import com.example.videoservice.dao.entities.Creator;
import com.example.videoservice.dao.entities.Video;
import com.example.videoservice.dao.repositories.CreatorRepository;
import com.example.videoservice.dao.repositories.VideoRepository;
import com.example.videoservice.dto.CreatorRequest;
import com.example.videoservice.dto.VideoRequest;
import com.example.videoservice.services.CreatorManager;
import com.example.videoservice.services.VideoManager;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;


import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQlController {
    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;
    private ModelMapper modelMapper;
    private CreatorManager creatorManager;
    private VideoManager videoManager;
    VideoGraphQlController(
            CreatorRepository creatorRepository,
            VideoRepository videoRepository,
            ModelMapper modelMapper,
            CreatorManager creatorManager,
            VideoManager videoManager
    ) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
        this.creatorManager = creatorManager;
        this.videoManager = videoManager;
    }
    @QueryMapping
    public List<Video> videoList() {
        return videoRepository.findAll();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                String.format("Creator %s not found", id)
                        )
                );
    }

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creatorRequest) {

        Creator creator = modelMapper.map(
                creatorRequest,
                Creator.class
        );

        return creatorRepository.save(creator);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoRequest videoRequest) {

        Creator creator = modelMapper.map(
                videoRequest.getCreator(),
                Creator.class
        );

        creatorRepository.save(creator);

        Video video = modelMapper.map(
                videoRequest,
                Video.class
        );

        video.setCreator(creator);

        return videoRepository.save(video);
    }

    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Random random = new Random();
                    CreatorRequest creatorRequest = CreatorRequest.builder().name("x" +
                                    new Random().nextInt())
                            .email("x@gmail.com").build();
                    Creator creator = creatorManager.saveCreator(creatorRequest);
                    Video video = videoManager.findById(1L);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);
                    return video;
                }));
    }
}