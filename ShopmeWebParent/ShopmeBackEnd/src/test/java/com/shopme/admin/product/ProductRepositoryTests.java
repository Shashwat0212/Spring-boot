package com.shopme.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
	@Autowired
	private ProductRepository prodRepo;
	@Autowired
	private TestEntityManager entityManager;

	// @Test
	// public void testCreateProduct() {
	// Brand brand = entityManager.find(Brand.class, 10);
	// Category category = entityManager.find(Category.class, 15);
	//
	// Product product = new Product();
	// product.setName("Samsung Galaxy A31");
	// product.setAlias("samsung_galaxy A31");
	// product.setShortDescription("Short description for Samsung");
	// product.setFullDescription("Full description for Samsung");
	//
	// product.setBrand(brand);
	// product.setCategory(category);
	//
	// product.setPrice(456);
	//
	// product.setCreatedTime(new Date());
	// product.setUpdatedTime(new Date());
	//
	// Product savedProduct = prodRepo.save(product);
	//
	// assertThat(savedProduct).isNotNull();
	// assertThat(savedProduct.getId()).isGreaterThan(0);
	// }
	// @Test
	// public void testListAllProducts() {
	// Iterable<Product> iterableProducts = prodRepo.findAll();
	//
	// iterableProducts.forEach(System.out::println);
	// }

	// @Test
	// public void testGetProduct() {
	// Integer id = 2;
	// Product prod = prodRepo.findById(id).get();
	// System.out.println(prod);
	// assertThat(prod).isNotNull();
	// }
	// @Test
	// public void testUpdateProduct() {
	// Integer id = 1;
	// Product prod = prodRepo.findById(id).get();
	// prod.setPrice(499);
	//
	// prodRepo.save(prod);
	//
	// Product updatedProduct = entityManager.find(Product.class, id);
	//
	// assertThat(updatedProduct.getPrice()).isEqualTo(499);
	// }
	// @Test
	// public void testDeleteProduct() {
	// Integer id = 3;
	// prodRepo.deleteById(id);
	//
	// Optional<Product> result = prodRepo.findById(id);
	//
	// assertThat(!result.isPresent());
	// }
}
