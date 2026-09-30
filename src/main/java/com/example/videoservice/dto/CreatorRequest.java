package com.example.videoservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatorRequest {
    private Long id;
    private String name;
    private String email;
}
