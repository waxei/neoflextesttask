package com.volkov.neoflextesttask.controller;

import com.volkov.neoflextesttask.dto.CalculateRequestDTO;
import com.volkov.neoflextesttask.dto.CalculateResponseDTO;
import com.volkov.neoflextesttask.model.CustomResponseEntity;
import com.volkov.neoflextesttask.service.CalculateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class CalculateController {

    public final CalculateService calculateService;

    @GetMapping("/calculate")
    public CustomResponseEntity<String> calculate(@RequestParam double annualSalary,
                                                  @RequestParam int vacationDayCount,
                                                  @RequestParam(required = false) String vacationStart) {
        var request = CalculateRequestDTO.builder()
                .annualSalary(annualSalary)
                .vacationDayCount(vacationDayCount)
                .vacationStart(vacationStart != null ? LocalDate.parse(vacationStart) : null)
                .build();

        CalculateResponseDTO response = calculateService.calculateVacationPayment(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new CustomResponseEntity<>(response.toString(), headers, HttpStatus.OK);
    }
}