package com.tungth.tuyensinh_be.repositoty;

import com.tungth.tuyensinh_be.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByCategoryAndSubcategoryOrderByPublishedDateDesc(String category, String subcategory, Pageable pageable);

    Page<Article> findByTitleContainingIgnoreCaseOrderByPublishedDateDesc(String title, Pageable pageable);

    boolean existsById(Long id);
}
