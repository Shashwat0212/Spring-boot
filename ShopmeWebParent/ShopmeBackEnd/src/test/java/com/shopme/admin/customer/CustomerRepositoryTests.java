package com.shopme.admin.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {
	@Autowired
	CustomerRepository repo;

	// @Test
	// public void testFindAll() {
	// Iterable<Customer> customerList = repo.findAll();
	// for (Customer customer : customerList) {
	// System.out.println(customer);
	// }
	//
	// }
	// @Test
	// public void testFindByEmail() {
	// Customer result = repo.findByEmail("shashwat0212@gmail.com");
	// assertThat(result).isNotNull();
	// }
	@Test
	public void testUpdateEnable() {
		repo.updateEnabledStatus(7, true);
	}
}
