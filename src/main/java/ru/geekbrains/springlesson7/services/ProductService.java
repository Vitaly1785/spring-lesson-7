package ru.geekbrains.springlesson7.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.springlesson7.models.Product;
import ru.geekbrains.springlesson7.productDao.ProductDao;

import java.util.Optional;


@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }
    @Transactional
    public Page<Product> showAll(Pageable pageable){
        return productDao.findAll(pageable);
    }
    @Transactional
    public Optional<Product> findById(Long id){
        return productDao.findById(id);

    }
    @Transactional
    public void createProduct(Product product){
        productDao.save(product);
    }
    @Transactional
    public void updateProduct(Product product, Long id){
        Optional<Product> updatableProduct = productDao.findById(id);
        if (updatableProduct.isPresent()) {
            updatableProduct.get().setPrice(product.getPrice());
            updatableProduct.get().setTitle(product.getTitle());
            productDao.save(updatableProduct.get());
        }
    }
    @Transactional
    public void deleteProduct(Long id){
        Optional<Product> deleteProduct = productDao.findById(id);
        deleteProduct.ifPresent(productDao::delete);
    }
    @Transactional
    public Page<Product> getProductsMinPrice(Pageable pageable){
        return productDao.findAllByOrderByPriceDesc(pageable);
    }
    @Transactional
    public Page<Product> getProductMaxPrice(Pageable pageable){
        return productDao.findAllByOrderByPriceAsc(pageable);
    }
}
