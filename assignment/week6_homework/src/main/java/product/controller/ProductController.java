package product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.domain.Product;
import product.exception.ProductAdminException;
import product.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        Product result = productService.getProductById(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }


    @GetMapping("/find")
    public List<Product> findProduct(String name,
                                     Float price,
                                    Float stockQuantity){
        Map<String,Object> condition=new HashMap<>();
        if(name!=null) {
            condition.put("name","%"+name+"%");
        }
        if(price!=null) {
            condition.put("price",price);
        }
        if(stockQuantity!=null) {
            condition.put("stockQuantity",stockQuantity);
        }
        return productService.findProduct(condition);
    }



    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ProductAdminException {
        return ResponseEntity.ok(productService.addProduct(product));
    }


    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable long id,@RequestBody Product product) throws ProductAdminException {
        productService.updateProduct(id,product);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
    }

}
