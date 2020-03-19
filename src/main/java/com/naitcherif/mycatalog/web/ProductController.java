package com.naitcherif.mycatalog.web;

import com.naitcherif.mycatalog.entities.Product;
import com.naitcherif.mycatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(Model model,
                           @RequestParam(value = "page", defaultValue = "0") int currentPage,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        Page<Product> products = productRepository.findByDesignationContains(keyword, PageRequest.of(currentPage, size));
        model.addAttribute("productsPage", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("size", size);
        model.addAttribute("pages", new int[products.getTotalPages()]);
        return "products";
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    String deleteProduct(Long id, int page, int size, String keyword) {
        productRepository.deleteById(id);
        return "redirect:/products?page=" + page + "&size=" + size + "&keyword=" + keyword;
    }
}
