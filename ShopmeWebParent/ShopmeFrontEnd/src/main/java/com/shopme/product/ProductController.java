package com.shopme.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopme.category.CategoryService;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.Product;
import com.shopme.common.exception.CategoryNotFoundException;
import com.shopme.common.exception.ProductNotFoundException;

@Controller
public class ProductController {

	@Autowired
	private CategoryService catService;
	@Autowired
	private ProductService prodService;
	@GetMapping("/c/{category_alias}")
	public String viewCategoryFirstPage(
			@PathVariable("category_alias") String alias, Model model) {
		return viewCategoryByPage(alias, 1, model);
	}
	@GetMapping("/c/{category_alias}/page/{pageNum}")
	public String viewCategoryByPage(
			@PathVariable("category_alias") String alias,
			@PathVariable("pageNum") int pageNum, Model model) {
		try {
			Category category = catService.getCategory(alias);
			if (category == null) {
				return "error/404";
			}
			List<Category> listCategoryParents = catService
					.getCategoryParents(category);
			Page<Product> pageProducts = prodService.listByCategories(pageNum,
					category.getId());
			List<Product> listProducts = pageProducts.getContent();
			long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE
					+ 1;
			long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
			if (endCount > pageProducts.getTotalElements()) {
				endCount = pageProducts.getTotalElements();
			}
			model.addAttribute("totalPages", pageProducts.getTotalPages());
			model.addAttribute("currentPage", pageNum);
			model.addAttribute("startCount", startCount);
			model.addAttribute("endCount", endCount);
			model.addAttribute("totalItems", pageProducts.getTotalElements());
			model.addAttribute("listCategoryParents", listCategoryParents);
			model.addAttribute("pageTitle", category.getName());
			model.addAttribute("listProducts", listProducts);
			model.addAttribute("category", category);
			return "product/products_by_category";
		} catch (CategoryNotFoundException ex) {
			return "error/404";
		}
	}

	@GetMapping("/p/{product_alias}")
	public String viewProductDetail(@PathVariable("product_alias") String alias,
			Model model) {
		try {
			Product product = prodService.getProduct(alias);
			List<Category> listCategoryParents = catService
					.getCategoryParents(product.getCategory());
			model.addAttribute("listCategoryParents", listCategoryParents);
			model.addAttribute("product", product);
			model.addAttribute("pageTitle", product.getShortName());
			return "product/product_detail";
		} catch (ProductNotFoundException e) {
			return "error/404";
		}
	}

	@GetMapping("/search")
	public String searchFirstPage(@RequestParam("keyword") String keyword,
			Model model) {
		return searchByPage(keyword, model, 1);
	}

	@GetMapping("/search/page/{pageNum}")
	public String searchByPage(@RequestParam("keyword") String keyword,
			Model model,
			@PathVariable("pageNum") int pageNum) {
		Page<Product> pageProducts = prodService.search(keyword, pageNum);
		List<Product> listResult = pageProducts.getContent();

		long startCount = (pageNum - 1) * ProductService.SEARCH_RESULTS_PER_PAGE
				+ 1;
		long endCount = startCount + ProductService.SEARCH_RESULTS_PER_PAGE - 1;
		if (endCount > pageProducts.getTotalElements()) {
			endCount = pageProducts.getTotalElements();
		}
		model.addAttribute("totalPages", pageProducts.getTotalPages());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", pageProducts.getTotalElements());
		model.addAttribute("pageTitle", keyword + " - Search Result");

		model.addAttribute("keyword", keyword);
		model.addAttribute("listProducts", listResult);
		return "product/search_result";
	}
}
