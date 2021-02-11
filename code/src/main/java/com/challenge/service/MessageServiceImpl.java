package com.challenge.service;

import com.challenge.constants.Constants;
import com.challenge.dataSave.SatelliteRepository;
import com.challenge.dto.PositionDto;
import com.challenge.dto.ResponseDto;
import com.challenge.dto.SatelliteDto;
import com.challenge.exceptions.NotFoundException;
import com.challenge.util.geometry.Circle;
import com.challenge.util.geometry.Vector;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.IntStream;

@Service
public class MessageServiceImpl implements MessageService{

    @Override
    public PositionDto findPosition(SatelliteDto[] satellite) {
        return getPosition(satellite);
    }

    @Override
    public ResponseDto findPositionAndMessage(SatelliteDto[] satellites) {
        ResponseDto result = getPositionAndMessage(satellites);
        return result;
    }

    @Override
    public ResponseDto findPositionAndMessage() {
        ResponseDto result = getPositionAndMessage(SatelliteRepository.getSatellites());
        return result;
    }

    @Override
    public SatelliteDto saveSatelliteMessage(String satellite_name, SatelliteDto satelliteDto) {
        SatelliteDto toSave = new SatelliteDto();
        toSave.setName(satellite_name);
        toSave.setDistance(satelliteDto.getDistance());
        toSave.setMessage(satelliteDto.getMessage());
        SatelliteRepository.setSatellite(toSave);
        return toSave;
    }

    private ResponseDto getPositionAndMessage(SatelliteDto[] satellites){
        Arrays.stream(satellites).forEach(satellite -> {
            if(satellite.getDistance() == 0.0f){
                throw new NotFoundException("No hay suficiente informacion en los satelites para procesar la posicion");
            }
        });
        ResponseDto result = new ResponseDto();
        result.setPositionDto(getPosition(satellites));
        result.setMessage(getMessage(satellites));
        return result;
    }

    private PositionDto getPosition(SatelliteDto[] satellite) {
        try{
            Vector v1 = new Vector(Constants.KENOBI_X, Constants.KENOBI_Y);
            Circle c1 = new Circle(v1, satellite[0].getDistance());
            Vector v2 = new Vector(Constants.SKYWALKER_X, Constants.SKYWALKER_Y);
            Circle c2 = new Circle(v2, satellite[1].getDistance());
            Vector v3 = new Vector(Constants.SATO_X, Constants.SATO_Y);
            Circle c3 = new Circle(v3, satellite[2].getDistance());

            Vector[] result = c1.getIntersectionPoints(c2);

            double dx = result[0].x - c3.point.x;
            double dy = result[0].y - c3.point.y;
            float d1 = (float) Math.hypot(dy, dx);

            dx = result[1].x - c3.point.x;
            dy = result[1].y - c3.point.y;
            float d2 = (float) Math.hypot(dy, dx);

            if (Math.abs(d1-c3.radius) < 0.4) {
                return new PositionDto(result[0]);
            } else if (Math.abs(d2-c3.radius) < 0.4) {
                return new PositionDto(result[1]);
            }
            throw new NotFoundException("No fue posible procesar la posicion");
        }catch(Exception e){
            throw new NotFoundException("No fue posible procesar la posicion", e);
        }
    }

    private String getMessage(SatelliteDto[] satellite) {
        String[] msg1 = satellite[0].getMessage();
        String[] msg2 = satellite[1].getMessage();
        String[] msg3 = satellite[2].getMessage();
        String[] result = new String[satellite[0].getMessage().length];
        try {
        result = IntStream.range(0, result.length).mapToObj(i -> {
            if (msg1[i] != null && !msg1[i].equals("")) {
                return msg1[i];
            }else if (msg2[i] != null && !msg2[i].equals("")) {
                return msg2[i];
            }else if (msg3[i] != null && !msg3[i].equals("")) {
                return msg3[i];
            }else{
                return "";
            }
        }).toArray(String[]::new);
        return String.join(" ", result);
        } catch (Exception e){
            return "";
        }
    }

}
