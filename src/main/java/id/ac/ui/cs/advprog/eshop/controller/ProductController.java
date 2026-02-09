package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:list";
    }
    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }
    @GetMapping("/edit")
    public String editProductPage(Model model, @RequestParam String productId) {
        try {
            Product productFetched = service.findById(productId);
            model.addAttribute("productFetched", productFetched);

            Product product = new Product();
            product.setProductName(productFetched.getProductName());
            product.setProductQuantity(productFetched.getProductQuantity());
            model.addAttribute("product", product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:list";
        }
        return "EditProduct";
    }
}
