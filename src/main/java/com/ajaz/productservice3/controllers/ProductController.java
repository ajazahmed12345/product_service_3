package com.ajaz.productservice3.controllers;

import com.ajaz.productservice3.dtos.GenericProductDto;
import com.ajaz.productservice3.exceptions.NotFoundException;
import com.ajaz.productservice3.models.Product;
import com.ajaz.productservice3.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        GenericProductDto genericProductDto = productService.getProductById(id);
        if(genericProductDto == null){
            throw new NotFoundException("product does not exist.");
        }

        return genericProductDto;
    }

    @GetMapping()
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        List<GenericProductDto> genericProductDtos = productService.getAllProducts();
        if(genericProductDtos.isEmpty()){
            return new ResponseEntity<>(genericProductDtos, HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }

    public void updateProductById(){

    }

    public void deleteProductById(){

    }
}
