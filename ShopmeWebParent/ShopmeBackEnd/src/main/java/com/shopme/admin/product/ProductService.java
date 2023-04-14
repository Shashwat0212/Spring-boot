package com.shopme.admin.product;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Product;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class ProductService {

	@Autowired
	private ProductRepository prodRepo;

	public List<Product> listAll() {
		return (List<Product>) prodRepo.findAll();
	}

	public Product save(Product product) {
		if (product.getId() == null) {
			product.setCreatedTime(new Date());
		}

		if (product.getAlias() == null || product.getAlias().isEmpty()) {
			String defaultAlias = product.getName().replaceAll(" ", "-");
			product.setAlias(defaultAlias);
		} else {
			product.setAlias(product.getAlias().replaceAll(" ", "-"));
		}
		product.setUpdatedTime(new Date());

		return prodRepo.save(product);
	}
	public String checkUnique(Integer id, String name) {
		boolean isCreatingNew = (id == null || id == 0);
		Product productByName = prodRepo.findByName(name);
		if (isCreatingNew) {
			if (productByName != null) {
				return "Duplicated";
			}
		} else {
			if (productByName != null && productByName.getId() != id) {
				return "Duplicated";
			}
		}
		return "OK";
	}
	public void updateProductEnabledStatus(Integer id, boolean enabled) {
		prodRepo.updateEnabledStatus(id, enabled);
	}
	public void delete(Integer id) throws ProductNotFoundException {
		Long countById = prodRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new ProductNotFoundException(
					"Could not find any product wth ID " + id);
		}
		prodRepo.deleteById(id);
	}

	public Product get(Integer id) throws ProductNotFoundException {
		try {
			return prodRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ProductNotFoundException(
					"Could not find any product with ID " + id);
		}
	}
}
