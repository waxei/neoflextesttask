package com.volkov.neoflextesttask.service.impl;

import com.volkov.neoflextesttask.dto.CalculateRequestDTO;
import com.volkov.neoflextesttask.dto.CalculateResponseDTO;
import com.volkov.neoflextesttask.service.CalculateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class CalculateServiceImpl implements CalculateService {
    private boolean isWeekday(LocalDate localDate){ return localDate.getDayOfWeek().getValue() < 6;}
    private int calculateValidDays(LocalDate startDate, int vacationDayCount){
        int validCount = 0;
        LocalDate date = startDate;
        for (int i = 0; i < vacationDayCount; i++){
            if (isWeekday(date)){
                validCount++;
            }
            date = date.plusDays(1);
        }
        return validCount;
    }
    @Override
    public CalculateResponseDTO calculateVacationPayment(CalculateRequestDTO requestDTO) {
        double annualSalary = requestDTO.getAnnualSalary();
        int vacationDayCount = requestDTO.getVacationDayCount();
        LocalDate vacationStart = requestDTO.getVacationStart();

        double dailySalary = annualSalary / 365;

        if(vacationStart != null){
            return new CalculateResponseDTO(dailySalary * calculateValidDays(vacationStart, vacationDayCount));
        }

        return new CalculateResponseDTO(dailySalary * vacationDayCount);
    }
}
