package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.entity.Product;
import product.entity.Supplier;
import product.service.SupplierService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    //根据id查询供应商
    @GetMapping("/get/{id}")
    public ResponseEntity<Supplier> getById(long id){
        Supplier result=supplierService.getSupplierById(id);
        if(result==null){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(result);
        }
    }
    @PostMapping("/add")
    public Map<String,Object> addSupplier(@RequestBody Supplier s){
        Map<String,Object> map = new HashMap<>();
        try {
            Supplier result = supplierService.addSupplier(s);
            map.put("success",true);
            map.put("message","供应商新增成功");
            //return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","供应商新增失败");
            //return null;
        }
        return map;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateSupplier(@PathVariable long id,@RequestBody Supplier s){
        supplierService.updateSupplier(id, s);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
        supplierService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
