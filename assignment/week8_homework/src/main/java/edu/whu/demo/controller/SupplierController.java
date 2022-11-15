package edu.whu.demo.controller;

import edu.whu.demo.entity.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import edu.whu.demo.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Api(tags = "供应商管理")
@RestController
@RequestMapping("suppliers")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @ApiOperation("根据Id查询供应商")
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@ApiParam("供应商Id")@PathVariable long id){
        Supplier result = supplierService.getSupplier(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @ApiOperation("根据条件查询供应商")
    @GetMapping("")
    public Page<Supplier> findSupplier(@ApiParam("商品名称")String name,
                                       @ApiParam("页码")@RequestParam(defaultValue = "0")Integer pageNum,
                                       @ApiParam("每页记录数") @RequestParam(defaultValue = "10")Integer pageSize){
        return supplierService.findSuppliers(name, PageRequest.of(pageNum,pageSize,Sort.by("id")));
    }

    /**
     * 添加供应商
     * @param supplier
     * @return
     * @throws ProductAdminException 在Controller抛出异常，可以通过全局异常处理进行捕获
     */
    @ApiOperation("添加供应商")
    @PostMapping("")
    @PreAuthorize("hasAuthority('supplier/update')")
    public Supplier addSupplier(@RequestBody Supplier supplier) throws ProductAdminException {
            return supplierService.addSupplier(supplier);
    }

    @ApiOperation("修改供应商")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('supplier/update')")
    public void updateSupplier(@PathVariable long id,@RequestBody Supplier supplier) throws ProductAdminException {
        supplierService.updateSupplier(id,supplier);
    }

    @ApiOperation("删除供应商")
    @PreAuthorize("hasAuthority('supplier/update')")
    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable long id){
        supplierService.deleteSupplier(id);
    }

}
