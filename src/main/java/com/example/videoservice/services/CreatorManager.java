package com.example.videoservice.services;
import com.example.videoservice.dao.entities.Creator;
import com.example.videoservice.dao.repositories.CreatorRepository;
import com.example.videoservice.dto.CreatorRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CreatorManager {

    private final CreatorRepository creatorRepository;
    private final ModelMapper modelMapper;

    public CreatorManager(
            CreatorRepository creatorRepository,
            ModelMapper modelMapper
    ) {
        this.creatorRepository = creatorRepository;
        this.modelMapper = modelMapper;
    }

    public Creator saveCreator(CreatorRequest request) {
        Creator creator = modelMapper.map(request, Creator.class);
        return creatorRepository.save(creator);
    }
}