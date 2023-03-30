package com.shopme.admin.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Category;
@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepo;

	public List<Category> listAll() {
		List<Category> rootCategories = categoryRepo.findRootCategories();
		return listHeirarchicalCategories(rootCategories);
	}
	private List<Category> listHeirarchicalCategories(
			List<Category> rootCategories) {
		List<Category> heirarchicalCategories = new ArrayList<>();
		for (Category rootCategory : rootCategories) {
			heirarchicalCategories.add(Category.copyFull(rootCategory));
			Set<Category> children = rootCategory.getChildren();
			for (Category subCategory : children) {
				String name = "--" + subCategory.getName();
				heirarchicalCategories
						.add(Category.copyFull(subCategory, name));
				listSubHeirarchicalCategories(heirarchicalCategories,
						subCategory, 1);
			}
		}
		return heirarchicalCategories;
	}
	private void listSubHeirarchicalCategories(
			List<Category> heirarchicalCategories, Category parent,
			int subLevel) {
		Set<Category> children = parent.getChildren();
		int newSubLevel = subLevel + 1;
		for (Category subCategory : children) {
			String name = "";
			for (int i = 0; i < newSubLevel; i++) {
				name += "--";
			}
			name += subCategory.getName();
			heirarchicalCategories.add(Category.copyFull(subCategory, name));
			listSubHeirarchicalCategories(heirarchicalCategories, subCategory,
					newSubLevel);
		}
	}
	public Category save(Category category) {
		return categoryRepo.save(category);
	}
	public List<Category> listCategoriesUsedInForm() {
		List<Category> categoriesUsedInForm = new ArrayList<>();
		Iterable<Category> categoriesInDB = categoryRepo.findAll();

		for (Category category : categoriesInDB) {
			if (category.getParent() == null) {
				categoriesUsedInForm.add(Category.copyIdAndName(category));
				Set<Category> children = category.getChildren();
				for (Category subCategory : children) {
					String name = "--" + subCategory.getName();
					categoriesUsedInForm.add(
							Category.copyIdAndName(subCategory.getId(), name));
					listSubCategoriesUsedInForm(categoriesUsedInForm,
							subCategory, 1);
				}
			}
		}
		return categoriesUsedInForm;
	}
	private void listSubCategoriesUsedInForm(
			List<Category> categoriesUsedInForm, Category parent,
			int subLevel) {
		int newSubLevel = subLevel + 1;
		for (Category subCategory : parent.getChildren()) {
			String name = "";
			for (int i = 0; i < newSubLevel; i++) {
				name += "--";
			}
			name += subCategory.getName();
			categoriesUsedInForm
					.add(Category.copyIdAndName(subCategory.getId(), name));
			listSubCategoriesUsedInForm(categoriesUsedInForm, subCategory,
					newSubLevel);
		}
	}

}
