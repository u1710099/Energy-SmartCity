package com.idigital.epam.energy.service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseResident {
    private Resident result;
    private Boolean success;
}
