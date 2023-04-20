package com.shopme.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Product;
import com.shopme.common.exception.ProductNotFoundException;

@Service
public class ProductService {
	public static final int PRODUCTS_PER_PAGE = 10;
	public static final int SEARCH_RESULTS_PER_PAGE = 10;
	@Autowired
	private ProductRepository prodRepo;

	public Page<Product> listByCategories(int pageNum, Integer categoryId) {
		String categoryIdMatch = "-" + String.valueOf(categoryId) + "-";
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);

		return prodRepo.listByCategories(categoryId, categoryIdMatch, pageable);
	}
	public Product getProduct(String alias) throws ProductNotFoundException {
		Product product = prodRepo.findByAlias(alias);
		if (product == null) {
			throw new ProductNotFoundException(
					"Could not find any product with alias " + alias);
		}
		return product;
	}
	public Page<Product> search(String keyword, int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum - 1,
				SEARCH_RESULTS_PER_PAGE);
		return prodRepo.search(keyword, pageable);
	}
}
