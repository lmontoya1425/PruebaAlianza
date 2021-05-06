package com.pruebaalianza.co.business;

import java.util.List;

import com.pruebaalianza.co.dto.ClientDTO;
import com.pruebaalianza.co.dto.GeneralDTO;

public interface ClientService {

	public List<ClientDTO> findAllByName(String sharedKey);
	
	public GeneralDTO createClients (ClientDTO dtoIn);
	
 
}
