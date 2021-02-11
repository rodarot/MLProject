package com.challenge.MLProject;

import com.challenge.dto.ResponseDto;
import com.challenge.dto.SatelliteDto;
import com.challenge.exceptions.NotFoundException;
import com.challenge.service.MessageService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MlProjectApplicationTests {

	@Autowired
	private MessageService messageService;


	@Test
	void contextLoads() {
	}

	@Test
	public void testResponseMessageAndPosition(){
		SatelliteDto[] satelites = satellitesData();
		ResponseDto result = messageService.findPositionAndMessage(satelites);
		Assert.assertNotNull(result.getMessage());
		Assert.assertNotNull(result.getPositionDto());
		Assert.assertEquals("Este es un mensaje secreto", result.getMessage());
	}

	@Test
	public void testErrorResponseMessageAndPosition(){
		try{
			ResponseDto result = messageService.findPositionAndMessage();
		}catch (NotFoundException notFoundException){
			Assert.assertEquals("No hay suficiente informacion en los satelites para procesar la posicion", notFoundException.getMessage());
		}
	}

	private SatelliteDto[] satellitesData(){

		SatelliteDto[] satelites = new SatelliteDto[3];
		SatelliteDto sateliteKe = new SatelliteDto();
		sateliteKe.setName("kenobi");
		sateliteKe.setDistance(300);
		sateliteKe.setMessage(new String[]{"", "es", "un", "mensaje", "secreto"});
		satelites[0] = sateliteKe;

		SatelliteDto sateliteSk = new SatelliteDto();
		sateliteSk.setName("skywalker");
		sateliteSk.setDistance(320);
		sateliteSk.setMessage(new String[]{"", "es", "", "",  "secreto"});
		satelites[1] = sateliteSk;

		SatelliteDto sateliteSa = new SatelliteDto();
		sateliteSa.setName("sato");
		sateliteSa.setDistance(766);
		sateliteSa.setMessage(new String[]{"Este", "", "un",  "", ""});
		satelites[2] = sateliteSa;

		return satelites;
	}


}
