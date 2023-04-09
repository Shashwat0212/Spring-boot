package com.shopme.admin.brand;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Brand;

@Service
public class BrandService {
	@Autowired
	private BrandRepository brandRepo;

	public List<Brand> listAll() {
		return brandRepo.findAll();
	}
	public Brand save(Brand brand) {
		return brandRepo.save(brand);
	}

	public Brand get(Integer id) throws BrandNotFoundException {
		try {
			return brandRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new BrandNotFoundException(
					"Could not find any brand wth ID " + id);
		}
	}
	public void delete(Integer id) throws BrandNotFoundException {
		Long countById = brandRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new BrandNotFoundException(
					"Could not find any brand wth ID " + id);
		}
		brandRepo.deleteById(id);
	}
}
