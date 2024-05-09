package com.spa.Online.Spa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long price;

    @ElementCollection
    private List<String> images;
    private boolean available;
    private Integer quantity;//so luong con lai cua san pham
    private String duration;//do dai thoi gian dich vu


    private Date createDate;
}
