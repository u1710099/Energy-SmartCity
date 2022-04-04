package com.idigital.epam.energy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String homeNumber;
    private String street;
    private String district;

    @OneToOne(mappedBy = "address")
    @JsonIgnoreProperties(value = { "address"}, allowSetters = true)
    private Home home;
}
