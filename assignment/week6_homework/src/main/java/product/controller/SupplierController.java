package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.domain.Supplier;
import product.exception.ProductAdminException;
import product.service.SupplierService;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable long id){
        Supplier result = supplierService.getSupplier(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }




    @PostMapping("/add")
    public Supplier addProduct(@RequestBody Supplier supplier) throws ProductAdminException {
        return supplierService.addSupplier(supplier);
    }


    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable long id,@RequestBody Supplier supplier) throws ProductAdminException {
        supplierService.updateSupplier(id,supplier);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id){
        supplierService.deleteSupplier(id);
    }

}
