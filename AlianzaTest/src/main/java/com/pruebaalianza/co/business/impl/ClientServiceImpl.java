package com.pruebaalianza.co.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaalianza.co.business.ClientService;
import com.pruebaalianza.co.dto.ClientDTO;
import com.pruebaalianza.co.dto.GeneralDTO;
import com.pruebaalianza.co.persistence.entity.ClientEntity;
import com.pruebaalianza.co.persistence.repository.ClientRepository;
import com.pruebaalianza.co.utils.Constants;
import com.pruebaalianza.co.utils.MapperUtil;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Override
	public List<ClientDTO> findAllByName(String sharedKey) {

		List<ClientDTO> response = new ArrayList<>();

		List<ClientEntity> list = new ArrayList<>();
		if (sharedKey.isEmpty()) {
			list = clientRepo.findAll();
		} else {
			list = clientRepo.findAllById(sharedKey);
		}
		for (ClientEntity reg : list) {
			response.add(MapperUtil.convertToDto(reg, ClientDTO.class));
		}
		return response;
	}

	@Override
	public GeneralDTO createClients(ClientDTO dtoIn) {

		GeneralDTO response = new GeneralDTO();
		Optional<ClientEntity> client = clientRepo.findById(dtoIn.getSharedKey());
		if (client.isPresent()) {
			response.setMesagge(Constants.ERROR_CLIENT_EXISTS);
		} else {
			ClientEntity clientEntity = new ClientEntity();
			clientEntity = MapperUtil.convertToEntity(dtoIn, ClientEntity.class);
			clientRepo.save(clientEntity);
			response.setMesagge(Constants.CLIENTE_SAVE);
		}
		return response;
	}
 

}
