package controller;

import model.Category;
import model.Tag;
import service.CategoryService;
import service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagService tagService;

	
	@RequestMapping(value = "/category/{aliasName}")
	public String loadCategoryPage(HttpSession session,ModelMap map,@PathVariable String aliasName) throws UnsupportedEncodingException {
		System.out.println("/category/"+aliasName);
		aliasName = URLDecoder.decode(aliasName, "utf-8");
		Category category = categoryService.getCategoryByAlias(aliasName);
		
		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();;
		
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		

		map.put("categoryList", categoryList);
		map.put("ca", category);
		map.put("tagList", tagList);

		return "/blog/category/article_pager";
	}
}
