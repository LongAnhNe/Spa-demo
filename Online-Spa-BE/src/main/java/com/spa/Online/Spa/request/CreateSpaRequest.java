package com.spa.Online.Spa.request;

import com.spa.Online.Spa.model.Address;
import com.spa.Online.Spa.model.ContractInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateSpaRequest {
    private Long id;
    private String name;
    private String description;
    private String serviceType;
    private Address address;
    private ContractInformation contractInformation;
    private String openingHours;
    private List<String> images;
}
