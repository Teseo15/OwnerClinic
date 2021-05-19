package com.tecsup.petclinic.veterinario;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VetRepository  extends CrudRepository<Veterinario,Long>{
	List<Veterinario> findByName(String name);
	List<Veterinario> findByLastName(String last_name);
}
