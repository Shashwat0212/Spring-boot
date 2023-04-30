package com.shopme.admin.setting.country;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.customer.CustomerService;
import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;
import com.shopme.common.exception.CustomerNotFoundException;

@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping("/customers")
	public String listFirstPage(Model model) {
		return listByPage(model, 1, "firstName", "asc", null);
	}

	@GetMapping("/customers/page/{pageNum}")
	public String listByPage(Model model,
			@PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("srtDir") String sortDir, @Param("keyword") String keyword) {
		System.out.println("SortField: " + sortField);
		System.out.println("Sort Dir: " + sortDir);
		Page<Customer> page = customerService.listByPage(pageNum, sortField,
				sortDir, keyword);
		List<Customer> listCustomers = page.getContent();
		long startCount = (pageNum - 1) * CustomerService.CUSTOMERS_PER_PAGE
				+ 1;
		long endCount = startCount + CustomerService.CUSTOMERS_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listCustomers", listCustomers);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("moduleURL", "/customers");
		return "customers/customers";
	}
	@PostMapping("/customers/save")
	public String saveCustomer(Customer customer,
			RedirectAttributes redirectAttributes) throws IOException {
		customerService.save(customer);
		redirectAttributes.addFlashAttribute("message",
				"The customer has been saved successfully.");
		return "redirect:/customers";
	}

	@GetMapping("/customers/edit/{id}")
	public String editCustomer(@PathVariable(name = "id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {
		try {
			Customer customer = customerService.get(id);
			List<Country> countries = customerService.listAllCountries();

			model.addAttribute("listCountries", countries);
			model.addAttribute("customer", customer);
			model.addAttribute("pageTitle", "Edit Customer (ID: " + id + ")");
			return "customers/customer_form";
		} catch (CustomerNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			return "redirect:/customers";
		}
	}
	@GetMapping("/customers/{id}/enabled/{status}")
	public String updateCustomerEnabledStatus(@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes) {
		customerService.updateCustomerEnabledStatus(id, enabled);
		String status = enabled ? "enabled" : "disabled";
		String message = "The Customer ID " + id + " has been " + status;
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/customers";
	}

	@GetMapping("/customers/detail/{id}")
	public String viewCustomer(@PathVariable("id") Integer id, Model model,
			RedirectAttributes ra) {
		try {
			Customer customer = customerService.get(id);
			model.addAttribute("customer", customer);

			return "customers/customer_detail_modal";
		} catch (CustomerNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return "redirect:/customers";
		}
	}
	@GetMapping("/customers/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id,
			RedirectAttributes ra) {
		try {
			customerService.delete(id);
			ra.addFlashAttribute("message", "The customer ID " + id
					+ " has been deleted successfully.");

		} catch (CustomerNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}

		return "redirect:/customers";
	}
}
