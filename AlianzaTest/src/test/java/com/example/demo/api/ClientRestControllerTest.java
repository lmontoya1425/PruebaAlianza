package com.example.demo.api;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.utils.Constants;
import com.pruebaalianza.co.dto.ClientDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientRestControllerTest {

	@BeforeClass
	public static void setup() {
		RestAssured.basePath = Constants.BASE_PATH_FONDOS;
		RestAssured.baseURI = Constants.BASE_URI;
	}

	@Test
	public void getClient_ok() {
		try {

			RestAssured.given()
					.contentType(ContentType.JSON).log().all()
					.pathParam(Constants.SHAREDKEY_PARAM, Constants.SHAREDKEY_VAL)
					.port(Constants.PUERTO_CONSULTAS)
					.when()
					.get(Constants.URL_GET_CLIENTS)
					.then()
					.log()
					.all()
					.statusCode(200);
		} catch (Exception eGetByShared) {
			return;
		}
	}

	
	@Test
	public void createClient_ok() {
		try {
 
			DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
			Date convertido = fechaHora.parse(Constants.DATA_ADDED_TEST);

			ClientDTO dtoIn = new ClientDTO();

			dtoIn.setBusinessId(Constants.BUSINESS_ID_TEST);
			dtoIn.setDataAdded(convertido);
			dtoIn.setEmail(Constants.EMAIL_TEST);
			dtoIn.setPhone(Constants.PHONE_TEST);
			dtoIn.setSharedKey(Constants.SHAREDKEY_TEST);           

			RestAssured.given().
                    contentType(ContentType.JSON)
                    .log()
                    .all() 
                    .body(dtoIn)
                    .port(Constants.PUERTO_CONSULTAS)
                    .when()
                    .post(Constants.URL_CEATE_CLIENTS)
                    .then()
                    .log()
                    .all()
                    .statusCode(200);
		} catch (Exception eCreateByUsuario) {
        	return;
		}
	}
	
	
	
}
