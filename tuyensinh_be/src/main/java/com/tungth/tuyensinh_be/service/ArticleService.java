package com.tungth.tuyensinh_be.service;

import com.tungth.tuyensinh_be.dto.ArticleCreateRequest;
import com.tungth.tuyensinh_be.dto.ArticleResponse;
import com.tungth.tuyensinh_be.entity.Article;
import com.tungth.tuyensinh_be.repositoty.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    // Lấy danh sách bài viết theo catergory
    public Map<String, Object> getArticlesByCategoryAndSubcategory(String category, String subcategory, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> articlePage = articleRepository.findByCategoryAndSubcategoryOrderByPublishedDateDesc(category, subcategory, pageable);

        // Convert LocalDate to ISO 8601 String
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        var articleData = articlePage.getContent().stream().map(article -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", article.getId());
            map.put("title", article.getTitle());
            map.put("summary", article.getSummary());
            map.put("category", article.getCategory());
            map.put("subcategory", article.getSubcategory());
            map.put("publishedDate", article.getPublishedDate().atStartOfDay().format(formatter));
            map.put("author", article.getAuthor());
            return map;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", articleData);
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", page);
        pagination.put("size", size);
        pagination.put("totalItems", articlePage.getTotalElements());
        pagination.put("totalPages", articlePage.getTotalPages());
        response.put("pagination", pagination);
        return response;
    }

    // Lấy danh sách TẤT CẢ các bài viết (cho giao diện admin xóa bài viết)
    public Map<String, Object> getAllArticles(int page, int size, String title) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> articlePage = title != null && !title.isEmpty() ?
                articleRepository.findByTitleContainingIgnoreCaseOrderByPublishedDateDesc(title, pageable) :
                articleRepository.findAll(pageable);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        var articleData = articlePage.getContent().stream().map(article -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", article.getId());
            map.put("title", article.getTitle());
            map.put("summary", article.getSummary());
            map.put("category", article.getCategory());
            map.put("subcategory", article.getSubcategory());
            map.put("publishedDate", article.getPublishedDate().atStartOfDay().format(formatter));
            map.put("author", article.getAuthor());
            return map;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", articleData);
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", page);
        pagination.put("size", size);
        pagination.put("totalItems", articlePage.getTotalElements());
        pagination.put("totalPages", articlePage.getTotalPages());
        response.put("pagination", pagination);
        return response;
    }

    @Transactional
    public Map<String, Object> deleteArticleById(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (!articleOptional.isPresent()) {
            response.put("status", "error");
            response.put("message", "Article not found");
            return response;
        }

        articleRepository.deleteById(id);
        response.put("status", "success");
        response.put("message", "Article deleted successfully");
        return response;
    }

    // Lấy bài viết theo id
    public Map<String, Object> getArticleById(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            Map<String, Object> articleData = new HashMap<>();
            articleData.put("id", article.getId());
            articleData.put("title", article.getTitle());
            articleData.put("coverImage", article.getCoverImage());
            articleData.put("summary", article.getSummary());
            articleData.put("content", article.getContent());
            articleData.put("category", article.getCategory());
            articleData.put("subcategory", article.getSubcategory());
            articleData.put("publishedDate", article.getPublishedDate().atStartOfDay().format(formatter));
            articleData.put("author", article.getAuthor());

            response.put("status", "success");
            response.put("data", articleData);
        } else {
            response.put("status", "error");
            response.put("message", "Article not found");
        }
        return response;
    }


    public ArticleResponse createArticle(ArticleCreateRequest request) {
        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setCoverImage(request.getCoverImage());
        article.setSummary(request.getSummary());
        article.setContent(request.getContent());
        article.setPublishedDate(request.getPublishedDate());
        article.setAuthor(request.getAuthor());
        article.setCategory(request.getCategory());
        article.setSubcategory(request.getSubcategory());

        Article savedArticle = articleRepository.save(article);

        return new ArticleResponse(
                savedArticle.getId(),
                savedArticle.getTitle(),
                savedArticle.getCoverImage(),
                savedArticle.getSummary(),
                savedArticle.getContent(),
                savedArticle.getPublishedDate(),
                savedArticle.getAuthor(),
                savedArticle.getCategory(),
                savedArticle.getSubcategory()
        );
    }
}
