package com.tungth.tuyensinh_be.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArticleResponse {
    private Long id;
    private String title;
    private String coverImage;
    private String summary;
    private String content;
    private LocalDate publishedDate;
    private String author;
    private String category;
    private String subcategory;

    public ArticleResponse(Long id, String title, String coverImage, String summary, String content,
                           LocalDate publishedDate, String author, String category, String subcategory) {
        this.id = id;
        this.title = title;
        this.coverImage = coverImage;
        this.summary = summary;
        this.content = content;
        this.publishedDate = publishedDate;
        this.author = author;
        this.category = category;
        this.subcategory = subcategory;
    }

}