package controller;

import model.*;
import service.ArticleService;
import service.CategoryService;
import service.TagService;
import util.Constant;
import util.JsonUtil;
import util.Pager;
import util.SuprUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;


@Controller
public class  ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TagService tagService;

	
	@RequestMapping(value = "/admin/article/list")
	public String list(HttpSession session,ModelMap map) {
		System.out.println("/admin/article/list");
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		if (manager == null) {
			return "redirect:/login";
		}
		map.put("manager", manager);
		
		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		System.out.println(categoryList.get(0).getCategoryName());
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		return "/admin/article/article_list";
	}
	
	@RequestMapping(value = "/my-blog/admin/article/load")
	public String loadArticlePageList(HttpSession session,ModelMap map,String param,Pager<Article> pager) throws UnsupportedEncodingException {
		System.out.println("/my-blog/admin/article/load  >>>>>>");
		return loadArticleList(session, map, param, pager);
	}

	@RequestMapping(value = "/admin/article/load")
	public String loadArticlePageList1(HttpSession session,ModelMap map,String param,Pager<Article> pager) throws UnsupportedEncodingException {
		System.out.println("/admin/article/load  >>>>>>");
		return loadArticleList(session, map, param, pager);
	}

	private String loadArticleList(HttpSession session,ModelMap map,String param,Pager<Article> pager) throws UnsupportedEncodingException {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);

		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		Article article = JsonUtil.fromJson(param, Article.class);
		if(!StringUtils.isEmpty(article.getTitle())){
			article.setTitle(URLDecoder.decode(article.getTitle(), "utf-8"));
		}
		paramMap.put("article", article);
		paramMap.put("manager", manager);
		List<Article> articleList = articleService.getArticleList(paramMap, pager);
		map.put("articleList", articleList);
		map.put("pager", pager);
		return "/admin/article/article_pager_list";
	}
	
	@RequestMapping(value = "/admin/article/addJump")
	public String addJump(HttpSession session,ModelMap map){
		System.out.println("/admin/article/addJump");
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		
		// 栏目列表  链接栏目
		List<Category> categoryList = categoryService.getCategoryList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
				
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		return "/admin/article/add_article";
	}
	
	@RequestMapping(value = "/admin/article/add")
	public @ResponseBody Result addArticle(String description,String content,String param,HttpSession session) throws UnsupportedEncodingException {
		System.out.println("/admin/article/add");
		//		System.out.println("before" + param);
		param = URLDecoder.decode(param,"utf-8");
//		System.out.println("after" + param);
		content = URLDecoder.decode(content,"utf-8");
		description = URLDecoder.decode(description,"utf-8");
		Article article = JsonUtil.fromJson(param, Article.class);
		article.setContent(content);
		article.setDescription(description);
		if(articleService.addArticle(article) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
	
	@RequestMapping(value = "/article/loadPage/{categoryId}/{articleId}")
	public String loadPage(HttpSession session,ModelMap map,@PathVariable String articleId,@PathVariable String categoryId) throws UnsupportedEncodingException {
		System.out.println("/article/loadPage/"+articleId+"/"+categoryId);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("categoryId", categoryId);
		paramMap.put("articleId", articleId);
		// 最新的文章列表
		List<Article> articleList = articleService.getLastArticleList(paramMap);

//System.out.println(articleList.get(2).getContent());
		if(!SuprUtil.isEmptyCollection(articleList)){
			int i = 1;
			for(Article article : articleList){
				// 获取标签
				List<Tag> tList = tagService.getArticleTagList(String.valueOf(article.getId()));
				article.setTagList(tList);
				// 获取图片
				String imageUrl = articleService.getArticleImageUrl(String.valueOf(article.getId()));
				article.setImageUrl(imageUrl);
				
				if(i == articleList.size()){
					map.put("articleId", article.getId());
				}
				i++;
			}
		}
		map.put("articleList", articleList);
		return "/blog/article/article_pager";
	}
	
	@RequestMapping(value = "/article/html/{id}")
	public String articleDetail(HttpSession session, ModelMap map,@PathVariable String id) {
		System.out.println("/article/html/"+id);
		// 更新文章的浏览数
		articleService.incrArticleShowCount(id);
		
		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		// 文章详情
		Article article = articleService.getArticleById(id);


		// 获取标签
		List<Tag> tList = tagService.getArticleTagList(id);
		article.setTagList(tList);
		
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("articleId", id);
		param.put("categoryId", article.getCategoryId());
		// 获取上一个文章 下一个文章id
		Article beforeArticle = articleService.getBeforeArticle(param);
		Article nextArticle = articleService.getNextArticle(param);
		
		// 获取相关文章  4个  同类型的随机四个
		List<Article> relationList = articleService.getLastArticleList(param);
		for(Article a : relationList){
			// 获取图片
			String imageUrl = articleService.getArticleImageUrl(String.valueOf(a.getId()));
			a.setImageUrl(imageUrl);
		}
		
		map.put("beforeArticle", beforeArticle);
		map.put("nextArticle", nextArticle);
		map.put("article", article);
		map.put("relationList", relationList);
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);

		return "/blog/article/article_detail";
	}
	
	
	
	
}
