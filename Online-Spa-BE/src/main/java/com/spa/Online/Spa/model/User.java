package com.spa.Online.Spa.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spa.Online.Spa.domain.USER_ROLE;
import com.spa.Online.Spa.dto.SpaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
@Id
@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private String email;
    private String password;
    

    private USER_ROLE role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Order> order;

    @ElementCollection
    private List<SpaDTO> favorate=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    private String status;


}
