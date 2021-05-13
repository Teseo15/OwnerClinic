package com.tecsup.petclinic.service;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.OwnerRepository;
import com.tecsup.petclinic.exception.PetNotFoundException;

public class OwnerServiceImpl implements OwnerService{
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);
	@Autowired
	OwnerRepository ownerRepository;

	@Override
	public Owner create(Owner owner) {
		
		return ownerRepository.save(owner);
	}

	@Override
	public Owner update(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws PetNotFoundException {
		Owner owner = findById(id);
		ownerRepository.delete(owner);
		
	}

	@Override
	public Owner findById(long id) throws PetNotFoundException {
		Optional<Owner> owner = ownerRepository.findById(id);

		if ( !owner.isPresent())
			throw new PetNotFoundException("Record not found...!");
			
		return owner.get();
	}

	@Override
	public List<Owner> findByName(String name) {
		List<Owner> owners = ownerRepository.findByName(name);

		owners.stream().forEach(owner->logger.info("" + owner));

		return owners;
	}



	@Override
	public Iterable<Owner> findAll() {
		// TODO Auto-generated method stub
		return ownerRepository.findAll();
	}

}
