package com.challenge.dataSave;

import com.challenge.constants.Constants;
import com.challenge.dto.SatelliteDto;

import java.util.Arrays;

public class SatelliteRepository {

    private static SatelliteDto[] satellites;

    public static SatelliteDto[] getSatellites() {
        return satellites;
    }

    static {
        satellites = new SatelliteDto[3];
        satellites[0] = new SatelliteDto("Kenobi");
        satellites[1] = new SatelliteDto("Skywalker");
        satellites[2] = new SatelliteDto("Sato");

    }

    public static void setSatellite(SatelliteDto toSave) {
        Arrays.stream(satellites).filter(satellite -> satellite.getName().equalsIgnoreCase(toSave.getName()))
                .findFirst().ifPresent(i -> {
                    i.setDistance(toSave.getDistance());
                    i.setMessage(toSave.getMessage());
                });
    }
}
