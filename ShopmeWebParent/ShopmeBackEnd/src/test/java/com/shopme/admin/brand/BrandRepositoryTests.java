package com.shopme.admin.brand;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.admin.category.CategoryRepository;
import com.shopme.common.entity.Brand;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class BrandRepositoryTests {
	@Autowired
	BrandRepository brandRepo;
	@Autowired
	CategoryRepository catRepo;

	// @Test
	// public void testCreateBrand() {
	// Set<Category> categories = new HashSet<>();
	// Category categoryCell = catRepo.findByName("Memory");
	// Category categoryTab = catRepo.findByName("Internal Hard Drives");
	// categories.add(categoryCell);
	// categories.add(categoryTab);
	// Brand brand = new Brand("Samsung", "brand-logo.png", categories);
	// Brand savedBrand = brandRepo.save(brand);
	// assertThat(savedBrand.getId()).isGreaterThan(0);
	// }
	// @Test
	// public void testFindAllBrands() {
	// List<Brand> brands = brandRepo.findAll();
	// for (Brand brand : brands) {
	// System.out.print(brand.getId() + " " + brand.getName() + " ");
	// Set<Category> categories = brand.getCategories();
	// for (Category category : categories) {
	// System.out.print(category.getName() + ", ");
	// }
	// System.out.println();
	// }
	// }

	// @Test
	// public void testGetById() {
	// Brand brand = brandRepo.getById(1);
	// assertThat(brand.getId()).isGreaterThan(0);
	// }
	// @Test
	// public void testUpdateBrand() {
	// Brand brand = brandRepo.getByName("Samsung");
	// Integer brandId = brand.getId();
	// String newName = "Samsung Electronics";
	// brandRepo.updateByName(brandId, newName);
	// // assertThat(updatedBrand.getId()).isGreaterThan(0);
	// }
	@Test
	public void testDeleteById() {
		Brand brand = brandRepo.getById(1);
		brandRepo.deleteById(brand.getId());

	}
}
