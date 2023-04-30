package com.shopme.admin.brand;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shopme.admin.paging.SearchRepository;
import com.shopme.common.entity.Brand;

public interface BrandRepository
		extends
			SearchRepository<Brand, Integer>,
			CrudRepository<Brand, Integer> {

	@Query("SELECT b FROM Brand b WHERE b.id = :id")
	public Brand getById(@Param("id") Integer id);

	@Query("SELECT b FROM Brand b WHERE b.name = :name")
	public Brand getByName(@Param("name") String name);

	@Query("UPDATE Brand b SET b.name = ?2 WHERE b.id = ?1")
	@Modifying
	public void updateByName(Integer id, String name);

	public Long countById(Integer id);

	@Query("SELECT b FROM Brand b WHERE b.name LIKE %?1%")
	public Page<Brand> findAll(String keyword, Pageable pageable);

	@Query("SELECT NEW Brand(b.id, b.name) FROM Brand b ORDER BY b.name ASC")
	public List<Brand> findAll();

}
