package com.volkov.neoflextesttask.service;

import com.volkov.neoflextesttask.dto.CalculateRequestDTO;
import com.volkov.neoflextesttask.dto.CalculateResponseDTO;

public interface CalculateService {
    CalculateResponseDTO calculateVacationPayment(CalculateRequestDTO requestDTO);
}
