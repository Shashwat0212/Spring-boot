package com.shopme.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {
	@Autowired
	ProductService prodService;

	@PostMapping("/products/check_unique")
	public String checkUnique(@RequestParam("id") Integer id,
			@RequestParam("name") String name) {
		return prodService.checkUnique(id, name);
	}
}
