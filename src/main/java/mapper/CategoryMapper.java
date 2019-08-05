package mapper;

import model.Category;

import java.util.List;

public interface CategoryMapper {

	public List<Category> getCategoryList();

	public Category getCategoryByAlias(String aliasName);

}
