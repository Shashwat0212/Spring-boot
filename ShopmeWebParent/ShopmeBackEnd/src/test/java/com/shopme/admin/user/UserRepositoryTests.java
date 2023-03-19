package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User shashwat = new User("ss@gmail.com", "password", "shashwat", "hai");
		shashwat.addRole(roleAdmin);
		User savedUser = repo.save(shashwat);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateNewUserWithTwoRole() {
		User user1 = new User("user1@gmail.com", "password", "user1", "hai");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		user1.addRole(roleAssistant);
		user1.addRole(roleEditor);
		User savedUser = repo.save(user1);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User userShashwat = repo.findById(1).get();
		System.out.println(userShashwat);
		assertThat(userShashwat).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {
		User userShashwat = repo.findById(1).get();
		userShashwat.setEnabled(true);
		userShashwat.setEmail("newEmail@gmail.com");
		repo.save(userShashwat);
	}

	@Test
	public void testUpdateUserRoles() {
		User userUser1 = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesPerson = new Role(2);

		userUser1.getRoles().remove(roleEditor);
		userUser1.addRole(roleSalesPerson);
		repo.save(userUser1);
	}

	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}
}
