package com.tecsup.petclinic.owner;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerRepository
	extends CrudRepository<Owner, Long> {

	// Fetch firstname
	List<Owner> findByName(String name);

	// Fetchbuscar id
	List<Owner> findByTypeId(int typeId);

}