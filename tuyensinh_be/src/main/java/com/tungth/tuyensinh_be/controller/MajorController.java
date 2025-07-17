package com.tungth.tuyensinh_be.controller;

import com.tungth.tuyensinh_be.entity.Major;
import com.tungth.tuyensinh_be.repositoty.MajorRepository;
import com.tungth.tuyensinh_be.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/majors")
public class MajorController {

    @Autowired
    private MajorRepository majorRepository;

    @GetMapping
    public Map<String, Object> getAllMajors() {
        List<Major> majors = majorRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", majors);
        return response;
    }


}
