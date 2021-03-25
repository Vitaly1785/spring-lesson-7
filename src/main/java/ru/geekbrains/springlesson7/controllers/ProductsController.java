package ru.geekbrains.springlesson7.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springlesson7.models.Product;
import ru.geekbrains.springlesson7.services.ProductService;


@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;
    private String sortMethod = "ASC";
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showAll(@PageableDefault(size = 10) Pageable pageable,
                            Model model){
        Page<Product> page = sortProduct(pageable);
        model.addAttribute("page", page);
//        model.addAttribute("sort", sortMethod);
        return "products/show";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "products/id";
    }
    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "products/new";
    }
    @PostMapping
    public String addProduct(@ModelAttribute("product") Product product){
        productService.createProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/{id}/edit")
    public String editProduct(Model model, @PathVariable("id") long id){
        model.addAttribute("product", productService.findById(id).get());
        return "products/edit";
    }
    @PatchMapping("/{id}")
    public String updateProduct(@ModelAttribute("product") Product product, @PathVariable("id") long id){
       productService.updateProduct(product, id);
        return "redirect:/products";
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping("/{sorted}")
    public String getSortMethod(@PathVariable("sorted") String sorted){
        sortMethod = sorted;
        return "redirect:/products";
    }

    public Page<Product> sortProduct(Pageable pageable) {
        Page<Product> products = null;

                switch (sortMethod) {
                    case "ASC":
                        products = productService.getProductMaxPrice(pageable);
                        break;
                    case "DESC":
                        products = productService.getProductsMinPrice(pageable);
                        break;
                    default:
                        products = productService.showAll(pageable);
                        break;
                }
        return products;
        }
    }

