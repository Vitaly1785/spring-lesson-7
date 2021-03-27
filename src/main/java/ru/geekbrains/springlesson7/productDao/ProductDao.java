package ru.geekbrains.springlesson7.productDao;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.springlesson7.models.Product;

import java.util.List;
import java.util.Optional;


public interface ProductDao extends CrudRepository<Product, Long> {
    Iterable<Product>findAllByOrderByPriceAsc();
    Iterable<Product>findAllByOrderByPriceDesc();
}
