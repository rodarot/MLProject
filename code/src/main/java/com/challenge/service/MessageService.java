package com.challenge.service;

import com.challenge.dto.PositionDto;
import com.challenge.dto.ResponseDto;
import com.challenge.dto.SatelliteDto;
import com.challenge.exceptions.NotFoundException;

public interface MessageService {
    PositionDto findPosition(SatelliteDto[] satellite);

    ResponseDto findPositionAndMessage(SatelliteDto[] satellite) throws NotFoundException;

    ResponseDto findPositionAndMessage();

    SatelliteDto saveSatelliteMessage(String satellite_name, SatelliteDto satelliteDto);
}
