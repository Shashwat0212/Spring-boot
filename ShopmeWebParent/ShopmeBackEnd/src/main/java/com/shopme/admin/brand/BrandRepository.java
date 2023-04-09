package com.shopme.admin.brand;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.shopme.common.entity.Brand;

public interface BrandRepository
		extends
			PagingAndSortingRepository<Brand, Integer>,
			CrudRepository<Brand, Integer> {

	public List<Brand> findAll();

	@Query("SELECT b FROM Brand b WHERE b.id = :id")
	public Brand getById(@Param("id") Integer id);

	@Query("SELECT b FROM Brand b WHERE b.name = :name")
	public Brand getByName(@Param("name") String name);

	@Query("UPDATE Brand b SET b.name = ?2 WHERE b.id = ?1")
	@Modifying
	public void updateByName(Integer id, String name);

	public Long countById(Integer id);

}
