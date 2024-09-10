package com.volkov.neoflextesttask.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Builder
public class CalculateRequestDTO {

    double annualSalary;
    int vacationDayCount;
    LocalDate vacationStart;

}
