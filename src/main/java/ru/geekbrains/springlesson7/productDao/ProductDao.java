package ru.geekbrains.springlesson7.productDao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.springlesson7.models.Product;



public interface ProductDao extends CrudRepository<Product, Long> {
    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Product>findAllByOrderByPriceDesc(Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}
