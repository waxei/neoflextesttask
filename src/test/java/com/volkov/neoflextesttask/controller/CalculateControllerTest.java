package com.volkov.neoflextesttask.controller;

import com.volkov.neoflextesttask.dto.CalculateRequestDTO;
import com.volkov.neoflextesttask.dto.CalculateResponseDTO;
import com.volkov.neoflextesttask.model.CustomResponseEntity;
import com.volkov.neoflextesttask.service.CalculateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CalculateControllerTest {

    @InjectMocks
    private CalculateController calculateController;

    @Mock
    private CalculateService calculateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculate_WithValidParameters_ShouldReturnResponse() {
        // Arrange
        double annualSalary = 60000;
        int vacationDayCount = 10;
        String vacationStart = "2024-09-01";
        CalculateResponseDTO mockResponse = new CalculateResponseDTO(2000.0);

        when(calculateService.calculateVacationPayment(any(CalculateRequestDTO.class))).thenReturn(mockResponse);

        // Act
        CustomResponseEntity<String> response = calculateController.calculate(annualSalary, vacationDayCount, vacationStart);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse.toString(), response.getBody());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        assertEquals(headers.toString(), response.getHeaders().toString());
    }
}