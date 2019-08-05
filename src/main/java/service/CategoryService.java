package service;

import mapper.CategoryMapper;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
	private CategoryMapper categoryMapper;
	
	public List<Category> getCategoryList() {
		List<Category> categoryList = categoryMapper.getCategoryList();
		return categoryList;
	}

	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
	@Autowired
	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	public Category getCategoryByAlias(String aliasName) {
		return categoryMapper.getCategoryByAlias(aliasName);
	}
	
}
