package com.tungth.tuyensinh_be.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArticleCreateRequest {
    private String title;
    private String coverImage;
    private String summary;
    private String content;
    private LocalDate publishedDate;
    private String author;
    private String category;
    private String subcategory;

}