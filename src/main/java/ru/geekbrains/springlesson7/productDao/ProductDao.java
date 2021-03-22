package ru.geekbrains.springlesson7.productDao;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.springlesson7.models.Product;

import java.util.Optional;

public interface ProductDao extends CrudRepository<Product, Long> {
    Optional<Product> findAllByOrderByPriceAsc();
    Optional<Product> findAllByOrderByPriceDesc();

}
