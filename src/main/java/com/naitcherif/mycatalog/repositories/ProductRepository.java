package com.naitcherif.mycatalog.repositories;

import com.naitcherif.mycatalog.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.designation like :x and p.price > :y")
    public Page<Product> search(
            @Param("x") String mc,
            @Param("y") Double price,
            Pageable pageable
    );

    public Page<Product> findByDesignationContains(String mc, Pageable pageable);
}
