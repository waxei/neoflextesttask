package com.volkov.neoflextesttask.controller;

import com.volkov.neoflextesttask.dto.CalculateRequestDTO;
import com.volkov.neoflextesttask.service.CalculateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class CalculateController {

    public final CalculateService calculateService;

    @GetMapping("/calculate")
    public String calculate(@RequestParam double annualSalary,
                            @RequestParam int vacationDayCount,
                            @RequestParam(required = false) String vacationStart){
        var request = CalculateRequestDTO.builder()
                .annualSalary(annualSalary)
                .vacationDayCount(vacationDayCount)
                .vacationStart(LocalDate.parse(vacationStart))
                .build();
        var response = calculateService.calculateVacationPayment(request);
        return response.toString();
    }

}
