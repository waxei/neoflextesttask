package com.volkov.neoflextesttask.service;

import com.volkov.neoflextesttask.dto.CalculateRequestDTO;
import com.volkov.neoflextesttask.dto.CalculateResponseDTO;
import com.volkov.neoflextesttask.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CalculateServiceTest {

    private final CalculateService calculateService = Mockito.mock(CalculateService.class);

    @Test
    public void testCalculateVacationPayment_WithValidParameters_ShouldCalculateCorrectly() {
        // Arrange
        double annualSalary = 60000;
        int vacationDayCount = 10;
        LocalDate vacationStart = LocalDate.of(2024, 9, 1);

        CalculateRequestDTO requestDTO = new CalculateRequestDTO(annualSalary, vacationDayCount, vacationStart);
        CalculateResponseDTO expectedResponse = new CalculateResponseDTO(1643.8356164383562);

        when(calculateService.calculateVacationPayment(requestDTO)).thenReturn(expectedResponse);

        // Act
        CalculateResponseDTO response = calculateService.calculateVacationPayment(requestDTO);

        // Assert
        assertEquals(expectedResponse.getVacationPayment(), response.getVacationPayment(), 0.001);
    }

    @Test
    public void testCalculateVacationPayment_WithoutVacationStart_ShouldCalculateCorrectly() {
        // Arrange
        double annualSalary = 60000;
        int vacationDayCount = 10;

        CalculateRequestDTO requestDTO = new CalculateRequestDTO(annualSalary, vacationDayCount, null);
        CalculateResponseDTO expectedResponse = new CalculateResponseDTO(1643.8356164383562);

        when(calculateService.calculateVacationPayment(requestDTO)).thenReturn(expectedResponse);

        // Act
        CalculateResponseDTO response = calculateService.calculateVacationPayment(requestDTO);

        // Assert
        assertEquals(expectedResponse.getVacationPayment(), response.getVacationPayment(), 0.001);
    }
}