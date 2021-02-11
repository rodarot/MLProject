package com.challenge.controller;

import com.challenge.dto.ResponseDto;
import com.challenge.dto.SatelliteDto;
import com.challenge.exceptions.NotFoundException;
import com.challenge.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/topsecret", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseDto> topSecret(@RequestBody SatelliteDto[] satellite) throws NotFoundException {
        return new ResponseEntity<ResponseDto>(messageService.findPositionAndMessage(satellite), HttpStatus.OK);
    }

    @RequestMapping(value = "/topsecret_split/{satellite_name}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SatelliteDto> topSecretSplit(@PathVariable String satellite_name, @RequestBody SatelliteDto satelliteDto) throws NotFoundException {
        return new ResponseEntity<SatelliteDto>(messageService.saveSatelliteMessage(satellite_name, satelliteDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/topsecret_split", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResponseDto> topSecretSplit() throws NotFoundException {
        return new ResponseEntity<ResponseDto>(messageService.findPositionAndMessage(), HttpStatus.OK);
    }

}