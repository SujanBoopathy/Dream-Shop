package com.sujan.tech.dream_shop.controller;

import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {
  private final ProductRepository productRepository;
}
