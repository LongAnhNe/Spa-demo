package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.Product;

import java.util.List;

public interface ProductService {

    public Product save(Product product);

    public Product findById(Long id);

    public void deleteById(Long id);


    public List<?> findAll();

    public List<?> findByName(String name);

    public Product updateQuantity(Long id, int quantity);

    public void importQuantity(Long id, int quantity);

//    import list product quantity


}
