package com.spa.Online.Spa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User customer;//user mean customer
    private Long employeeId;//nhan vien hoan thanh hoa don
    private Long totalAmmount;
    private String orderStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @ManyToOne
    private Address deliveryAddress;
    @OneToMany
    private List<OrderItem> items;
    @OneToOne
    private Payment payment ;

    private int totalItem;

    private int totalPrice;



}
