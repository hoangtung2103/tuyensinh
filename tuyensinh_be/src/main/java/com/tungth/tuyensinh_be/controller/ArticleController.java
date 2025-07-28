package com.tungth.tuyensinh_be.controller;

import com.tungth.tuyensinh_be.dto.ArticleCreateRequest;
import com.tungth.tuyensinh_be.dto.ArticleResponse;
import com.tungth.tuyensinh_be.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody ArticleCreateRequest request) {
        try {
            ArticleResponse result = articleService.createArticle(request);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

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

    // Lấy danh sách TẤT CẢ các bài viết (cho giao diện admin xóa bài viết)
        // usage:
        //GET http://localhost:8081/api/articles/list?page=1&size=12&title=tuyển%20sinh
        //GET http://localhost:8081/api/articles/list?page=1&size=12
    @GetMapping("/list")
    public Map<String, Object> getAllArticles(
            @RequestParam int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String title) {
        return articleService.getAllArticles(page, size, title);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteArticleById(@PathVariable Long id) {
        return articleService.deleteArticleById(id);
    }
}
