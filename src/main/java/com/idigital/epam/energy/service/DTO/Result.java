package com.idigital.epam.energy.service.DTO;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class Result {
    private Long cardNumber;
    private List<HomeDto> homes;


}
