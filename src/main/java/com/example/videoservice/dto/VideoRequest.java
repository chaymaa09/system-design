package com.example.videoservice.dto;

import com.example.videoservice.dao.entities.Creator;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private Date datePublication;
    private CreatorRequest Creator;
}
