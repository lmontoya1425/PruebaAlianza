package com.pruebaalianza.co.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pruebaalianza.co.dto.ClientDTO;
import com.pruebaalianza.co.persistence.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	@Query(value = " select e "
			+ "      from ClientEntity e  "
			+ "		 where  e.sharedKey like %:sharedKey% " , nativeQuery = false)
	public List<ClientEntity> findAllById(String sharedKey);
	
	
}
