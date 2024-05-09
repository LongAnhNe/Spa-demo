package com.spa.Online.Spa.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.util.List;

@Data
@Embeddable


public class SpaDTO {
    private String tile;
    @Column(length = 1000)
    private List<String> images;
    private String description;
    private Long id;

}
