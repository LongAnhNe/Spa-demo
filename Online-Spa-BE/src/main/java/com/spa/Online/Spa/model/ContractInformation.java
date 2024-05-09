package com.spa.Online.Spa.model;

import jakarta.persistence.Entity;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Enabled
@AllArgsConstructor
@NoArgsConstructor
public class ContractInformation {
    private String mobile;
    private String email;
    private String twitter;
    private String instagram;
}
