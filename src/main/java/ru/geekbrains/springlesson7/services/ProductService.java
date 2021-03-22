package ru.geekbrains.springlesson7.services;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.springframework.data.domain.Sort;
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
    public Iterable<Product> showAll(){
        return productDao.findAll();
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
    public Optional<Product> getProductsMinPrice(){
        return productDao.findAllByOrderByPriceDesc();
    }
    @Transactional
    public Optional<Product> getProductMaxPrice(){
        return productDao.findAllByOrderByPriceAsc();
    }
}
