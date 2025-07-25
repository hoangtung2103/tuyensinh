package com.tungth.tuyensinh_be.controller;

import com.tungth.tuyensinh_be.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // Lấy danh sách bài viêt theo catergory
    @GetMapping("/{category}/{subcategory}/{page}")
    public Map<String, Object> getArticlesByCategoryAndSubcategory(
            @PathVariable String category,
            @PathVariable String subcategory,
            @PathVariable int page,
            @RequestParam(defaultValue = "12") int size) {
        return articleService.getArticlesByCategoryAndSubcategory(category, subcategory, page, size);
    }

    // Lấy bài viết theo id
    @GetMapping("/{id}")
    public Map<String, Object> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }
}
