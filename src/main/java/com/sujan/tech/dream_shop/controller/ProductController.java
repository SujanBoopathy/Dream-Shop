package com.sujan.tech.dream_shop.controller;

import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("{api_url}/product")
public class ProductController {
  private final IProductService productService;


  public void getAllProducts(){
    return productService.getAllProducts();
  }
  
}
