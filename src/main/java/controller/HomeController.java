package controller;

import model.Category;
import model.Tag;
import service.CategoryService;
import service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagService tagService;
	
	@RequestMapping("/")
	public String home(ModelMap map) {
		List<Category> categoryList = categoryService.getCategoryList();
		List<Tag> tagList = tagService.getTagList();
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		return "/blog/home";
	}
}
