package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;


/**
 * 百度富文本编辑器ueditor使用demo
 */
@Controller
@RequestMapping("/test")
public class TestController {
    //文章标题
    private String title;
    //暂存页面数据
    private String content;

    //增加文章测试
    @RequestMapping(value = "/addArticle")
    public String addArticle() {
        System.out.println("addArticle...");
        return "/test/add_article_test";
    }

    //增加文章测试
    @RequestMapping(value = "/add")
    public String add(String description,String content) throws UnsupportedEncodingException {
        System.out.println("add...");
        System.out.println("content："+content);
        this.content = content;

        return "redirect:/test/detail";
    }

//    文章详情
    @RequestMapping(value = "/detail")
    public String detail(Model model) {
        System.out.println("detail...");
        model.addAttribute("content", content);
        return "/test/article_detail";
    }



}
