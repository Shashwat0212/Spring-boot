package com.shopme.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AddressRepositoryTests {

	@Autowired
	private AddressRepository repo;

	// @Test
	// public void testAddNew() {
	// Integer customerId = 2;
	// Integer countryId = 234; // USA
	//
	// Address newAddress = new Address();
	// newAddress.setCustomer(new Customer(customerId));
	// newAddress.setCountry(new Country(countryId));
	// newAddress.setFirstName("Chicha");
	// newAddress.setLastName("Bhupendraaaa");
	// newAddress.setPhoneNumber("564857865");
	// newAddress.setAddressLine1("bagal wala gali chuaraha se");
	// newAddress.setAddressLine2("Novak Building");
	// newAddress.setCity("Chino");
	// newAddress.setState("California");
	// newAddress.setPostalCode("91710");
	//
	// Address savedAddress = repo.save(newAddress);
	//
	// assertThat(savedAddress).isNotNull();
	// assertThat(savedAddress.getId()).isGreaterThan(0);
	// }
	//
	// // @Test
	// // public void testFindByCustomer() {
	// // Integer customerId = 5;
	// // List<Address> listAddresses = repo
	// // .findByCustomer(new Customer(customerId));
	// // assertThat(listAddresses.size()).isGreaterThan(0);
	// //
	// // listAddresses.forEach(System.out::println);
	// // }
	// //
	// // @Test
	// // public void testFindByIdAndCustomer() {
	// // Integer addressId = 1;
	// // Integer customerId = 5;
	// //
	// // Address address = repo.findByIdAndCustomer(addressId, customerId);
	// //
	// // assertThat(address).isNotNull();
	// // System.out.println(address);
	// // }
	// //
	// @Test
	// public void testUpdate() {
	// Integer addressId = 3;
	// // String phoneNumber = "646-232-3932";
	//
	// Address address = repo.findById(addressId).get();
	// // address.setPhoneNumber(phoneNumber);
	// address.setDefaultForShipping(true);;
	//
	// Address updatedAddress = repo.save(address);
	// // assertThat(updatedAddress.getPhoneNumber()).isEqualTo(phoneNumber);
	// }
	// //
	// // @Test
	// // public void testDeleteByIdAndCustomer() {
	// // Integer addressId = 1;
	// // Integer customerId = 5;
	// //
	// // repo.deleteByIdAndCustomer(addressId, customerId);
	// //
	// // Address address = repo.findByIdAndCustomer(addressId, customerId);
	// // assertThat(address).isNull();
	// // }
	// //
	// @Test
	// public void testSetDefault() {
	// Integer addressId = 7;
	// repo.setDefaultAddress(addressId);
	//
	// Address address = repo.findById(addressId).get();
	// assertThat(address.isDefaultForShipping()).isTrue();
	// }
	// //
	// @Test
	// public void testSetNonDefaultAddresses() {
	// Integer addressId = 7;
	// Integer customerId = 2;
	// repo.setNonDefaultForOthers(addressId, customerId);
	// }
	// //
	// @Test
	// public void testGetDefault() {
	// Integer customerId = 5;
	// Address address = repo.findDefaultByCustomer(customerId);
	// assertThat(address).isNotNull();
	// System.out.println(address);
	// }
}
