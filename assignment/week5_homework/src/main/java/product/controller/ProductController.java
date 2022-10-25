package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.entity.Product;
import product.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    //根据id查询商品信息
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(long id){
        Product result=productService.getProductById(id);
        if(result==null){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(result);
        }
    }

    //添加商品
   @PostMapping("/add")
    public Map<String,Object> addProduct(@RequestBody Product product){
       Map<String,Object> map = new HashMap<>();
        try {
            Product result = productService.addProduct(product);
            map.put("success",true);
            map.put("message","用户新增成功");
            //return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户新增失败");
            //return null;
        }
       return map;
    }
   /* @PostMapping("/add")
    public Map<String,Object> register(@RequestBody Map<String,String> re_map){
        String name = re_map.get("name");
        int price = Integer.parseInt(re_map.get("price"));
        int stockQuantity = Integer.parseInt(re_map.get("stockQuantity"));

        Map<String,Object> map = new HashMap<>();
        try {
            Product p = new Product(name,price,stockQuantity);
            productService.addProduct(p);
            map.put("success",true);
            map.put("message","用户新增成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户新增失败");
        }
        return map;
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable long id,@RequestBody Product p){
        productService.updateProduct(id, p);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<Product>> findAll(@PathVariable long id){
        List<Product> result = productService.findProduct(id);
        return ResponseEntity.ok(result);
    }

    //满足条件的查找
    @GetMapping("")
    public ResponseEntity<List<Product>> findProduct(float price, float stockQuantity){
        List<Product> result = productService.findTodos(price,stockQuantity);
        return ResponseEntity.ok(result);
    }
}
