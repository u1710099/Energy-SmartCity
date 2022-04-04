package com.idigital.epam.energy.service.DTO;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Data
public class Response {
    private List<Result> result;
    private Boolean success;


}
