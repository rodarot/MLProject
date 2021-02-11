package com.challenge.dto;

public class ResponseDto {

    private PositionDto positionDto;
    private String message;


    public PositionDto getPositionDto() {
        return positionDto;
    }

    public void setPositionDto(PositionDto positionDto) {
        this.positionDto = positionDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
