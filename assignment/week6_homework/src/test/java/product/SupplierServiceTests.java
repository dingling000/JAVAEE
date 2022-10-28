package product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import product.Dao.SupplierMybatisPlusMapper;
import product.domain.Supplier;
import product.exception.ProductAdminException;
import product.service.SupplierService;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SupplierServiceTests {
    @Autowired
    SupplierService supplierService;

    @Autowired
    SupplierMybatisPlusMapper supplierMybatisPlusMapper;


    @BeforeEach
    public void init(){
        Supplier supplier=new Supplier();
        supplier.setName("京东");
        supplier.setPhone("123456");
        supplierMybatisPlusMapper.insert(supplier);

    }

    @Test
    public void testAdd() throws ProductAdminException {
        Supplier supplier=new Supplier();
        supplier.setName("阿里巴巴");
        supplier.setPhone("1234567");
        Supplier result=supplierService.addSupplier(supplier);
        assertNotNull(result);
        assertNotNull(supplierMybatisPlusMapper.selectById(result.getId()));

        //测试删除
        supplierService.deleteSupplier(result.getId());
        assertNull(supplierMybatisPlusMapper.selectById(result.getId()));
    }

}
