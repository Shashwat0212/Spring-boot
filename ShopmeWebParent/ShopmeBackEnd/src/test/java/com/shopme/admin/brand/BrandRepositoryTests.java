package com.shopme.admin.brand;

import static org.assertj.core.api.Assertions.assertThat;

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
	private BrandRepository brandRepo;
	@Autowired
	private CategoryRepository catRepo;

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
	@Test
	public void testFindAllBrands() {
		Iterable<Brand> brands = brandRepo.findAll();
		brands.forEach(System.out::println);
		assertThat(brands).isNotEmpty();
	}

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
