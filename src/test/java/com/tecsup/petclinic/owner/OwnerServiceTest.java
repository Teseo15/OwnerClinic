package com.tecsup.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.exception.PetNotFoundException;



@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
   	private OwnerService ownerService;
	
	@Test
	public void crear() {
		 String firstname="juan";
		 String lastname ="sanchés";
		 String address="220 W. Liberty";
		 String city="Madison";
		 String telephone="9985551023";
		Owner owner =new Owner(firstname,lastname,address,city,telephone);
		owner = ownerService.create(owner);
		logger.info("" + owner);

		assertThat(owner.getId()).isNotNull();
		assertEquals(firstname, owner.getFirstname());
		assertEquals(lastname, owner.getLastname());
		assertEquals(address, owner.getAddress());
		assertEquals(city, owner.getCity());
		assertEquals(telephone, owner.getTelephone());
	}
//BUSCAR POR ID
	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String NAME = "George";
		Owner owner = null;
		
		try {
			
			owner = ownerService.findById(ID);
			
		} catch (PetNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + owner);

		assertEquals(NAME,owner.getFirstname());

	}
	//BUSCAR POR NOMBRE
	@Test
	public void testFindOwnerByName() {

		String FINDNAME = "George";
		int SIZE_EXPECTED = 1;

		List<Owner> owner = ownerService.findByName(FINDNAME);

		assertEquals(SIZE_EXPECTED, owner.size());
	}
	
	@Test
	public void testDeletePet() {

		
		long ID = 1;
		

		Owner owner = new Owner(ID);

		try {
			ownerService.delete(owner.getId());
		} catch (PetNotFoundException e) {
			fail(e.getMessage());
		}
				
	}
	@Test
	public void testUpdate() {
		 String firstname="juan2";
		 String lastname ="sanchés2";
		 String address="220 W. Liberty";
		 String city="Madison";
		 String telephone="9985551023";

		 String upfirstname="juan3";
		 String uplastname ="sanchés3";
		 String upaddress="330 W. Liberty";
		 String upcity="Madison3";
		 String uptelephone="3335551023";
		 long create_id = -1;

		 Owner owner =new Owner(firstname,lastname,address,city,telephone);
			
			logger.info(">" + owner);
			Owner readowner = ownerService.create(owner);
		
		logger.info(">>" + readowner);

		create_id = readowner.getId();

		// Prepare data for update
		readowner.setFirstname(upfirstname);
		readowner.setLastname(uplastname);
		readowner.setAddress(upaddress);
		readowner.setCity(upcity);
		readowner.setTelephone(uptelephone);
		

		// Execute update
		Owner upgradeOwner = ownerService.update(readowner);
		logger.info(">>>>" + upgradeOwner);

		assertThat(create_id).isNotNull();
		
		assertEquals( upfirstname, upgradeOwner.getFirstname());
		assertEquals(uplastname, upgradeOwner.getLastname());
		assertEquals(upaddress, upgradeOwner.getAddress());
		assertEquals(upcity, upgradeOwner.getCity());
		assertEquals(uptelephone, upgradeOwner.getTelephone());
	}
}
