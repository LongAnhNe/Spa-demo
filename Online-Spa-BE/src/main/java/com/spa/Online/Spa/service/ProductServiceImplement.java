package com.spa.Online.Spa.service;

import com.spa.Online.Spa.model.Product;
import com.spa.Online.Spa.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceImplement implements ProductService {

    private ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        if(product.getId()==null) {
            product.setCreateDate(new Date());
        }
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){

            return product.get();
        }
        throw new RuntimeException("Product not found");
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);

    }


    @Override
    public List<?> findAll() {

        return productRepository.findAll();
    }

    @Override
    public List<?> findByName(String name) {

        return productRepository.findByName(name);
    }

    @Override
    public Product updateQuantity(Long id, int quantity) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product product1 = product.get();
            product1.setQuantity(quantity);
            return productRepository.save(product1);
        }
        throw new RuntimeException("Product not found");
    }

    @Override
    public void importQuantity(Long id, int quantity) {

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product product1 = product.get();
            product1.setQuantity(product1.getQuantity()+quantity);
            productRepository.save(product1);
        }

    }
}
