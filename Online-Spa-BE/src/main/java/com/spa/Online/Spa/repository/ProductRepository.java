package com.spa.Online.Spa.repository;

import com.spa.Online.Spa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductId(Long productId);

    @Query("SELECT p FROM Product p WHERE "
            + "p.name LIKE %:keyword%  "
/*            + "p.!=null"*/
    )
    List<Product> searchByName(@Param("keyword") String keyword);
    List<Product >findByName(String name);

}
