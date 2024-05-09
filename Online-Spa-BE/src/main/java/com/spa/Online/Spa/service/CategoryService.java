package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name,Long userId);
    public List<Category> findCategoryBySpaId(Long id) throws Exception;

}
