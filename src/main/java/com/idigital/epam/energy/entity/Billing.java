package com.idigital.epam.energy.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer energyConsumption;
    private Integer amountEnergyConsumption;
    private Integer sum;
    @ManyToOne
    @JsonIgnore
    private EnergyMeter energyMeter;

}
