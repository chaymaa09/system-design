package com.example.videoservice;
import com.example.videoservice.dao.entities.Creator;
import com.example.videoservice.dao.entities.Video;
import com.example.videoservice.dao.repositories.CreatorRepository;
import com.example.videoservice.dao.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class VideoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            CreatorRepository creatorRepository,
            VideoRepository videoRepository) {

        return args -> {
            List<Creator> creators = List.of(

                    Creator.builder()
                            .name("Ahmed")
                            .email("ahmed@gmail.com")
                            .build(),

                    Creator.builder()
                            .name("Salma")
                            .email("salma@gmail.com")
                            .build(),

                    Creator.builder()
                            .name("Yassine")
                            .email("yassine@gmail.com")
                            .build()
            );

            creatorRepository.saveAll(creators);

            List<Video> videos = List.of(

                    Video.builder()
                            .name("Introduction à Spring Boot")
                            .url("https://youtube.com/video1")
                            .description("Introduction à Spring Boot et JPA")
                            .datePublication(new Date())
                            .creator(creators.get(0))
                            .build(),

                    Video.builder()
                            .name("Introduction à GraphQL")
                            .url("https://youtube.com/video2")
                            .description("Découverte de GraphQL")
                            .datePublication(new Date())
                            .creator(creators.get(1))
                            .build(),

                    Video.builder()
                            .name("Spring Data JPA")
                            .url("https://youtube.com/video3")
                            .description("Cours sur Spring Data JPA")
                            .datePublication(new Date())
                            .creator(creators.get(0))
                            .build(),

                    Video.builder()
                            .name("Base de données H2")
                            .url("https://youtube.com/video4")
                            .description("Utilisation de H2 avec Spring Boot")
                            .datePublication(new Date())
                            .creator(creators.get(2))
                            .build()
            );

            videoRepository.saveAll(videos);

        };
    }
}