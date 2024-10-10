package com.telusko.ecom_proj.controller;

import com.telusko.ecom_proj.model.Product;
import com.telusko.ecom_proj.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProuct(@PathVariable int id){
        Product product=service.getProductById(id);
        if(product!=null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product=service.getProductById(productId);
        byte[] imageFile=product.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product,
                                                @RequestPart MultipartFile imageFile){
        Product product1;
        try{
        product1=service.updateProduct(id,product,imageFile);}
        catch (Exception e){
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }

        if(product1!=null){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product1=service.getProductById(id);
        if(product1!=null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else
            return new ResponseEntity<>("Failed to delete",HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> product=service.searchProduct(keyword);
        if(product!=null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}