package com.pruebaalianza.co.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaalianza.co.business.ClientService;
import com.pruebaalianza.co.dto.ClientDTO;
import com.pruebaalianza.co.dto.GeneralDTO;
import com.pruebaalianza.co.utils.Constants;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins= "*")
public class ClientRestController {

	private static final Logger logger = LoggerFactory.getLogger(ClientRestController.class);

	@Autowired
	private ClientService clientServie;
	
 
	@GetMapping(value = "/findClients", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ClientDTO>> getClients(HttpServletRequest req,
			@RequestParam(name = "sharedKey", required = true) String sharedKey) {

		logger.info(Constants.FORMATO_LOG, "Start Process: ", "Get Clients");

		List<ClientDTO> list = new ArrayList<ClientDTO>();
		list = clientServie.findAllByName(sharedKey);

		return new ResponseEntity<List<ClientDTO>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/createClient", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GeneralDTO> crear(@RequestBody(required = true) @Valid ClientDTO dtoIn,
			BindingResult result) {

		logger.info(Constants.FORMATO_LOG, "Start Process: ", "Create Client");
		GeneralDTO response = new GeneralDTO();  
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());
			response.setMesagge(errors.toString());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			response = clientServie.createClients(dtoIn);
		 return new ResponseEntity<GeneralDTO>(response, HttpStatus.CREATED); 
		}catch (DataAccessException e) {
			response.setMesagge(Constants.ERROR_SAVE);
			logger.info(Constants.FORMATO_LOG, Constants.ERROR, Constants.ERROR_SAVE);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
