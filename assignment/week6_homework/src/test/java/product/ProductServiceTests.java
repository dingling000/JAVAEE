package product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import product.Dao.ProductDtoMapping;
import product.Dao.ProductMybatisPlusMapper;
import product.Dao.SupplierMybatisPlusMapper;
import product.domain.Product;
import product.domain.ProductDto;
import product.domain.Supplier;
import product.exception.ProductAdminException;
import product.service.ProductDtoService;
import product.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    ProductDtoService productDtoService;
    @Autowired
    ProductMybatisPlusMapper productMybatisPlusMapper;
    @Autowired
    ProductDtoMapping productDtoMapping;
    @Autowired
    SupplierMybatisPlusMapper supplierMybatisPlusMapper;
    @Autowired
    ProductService productService;




    @BeforeEach
    private void initData(){
        Supplier supplier1=new Supplier();
        supplier1.setName("A公司");
        supplier1.setPhone("1982229992");
        supplierMybatisPlusMapper.insert(supplier1);

        Supplier supplier2=new Supplier();
        supplier2.setName("B公司");
        supplier2.setPhone("1982223332");
        supplierMybatisPlusMapper.insert(supplier2);


        Product product=new Product();
        product.setName("IPhone 14 pro");
        product.setPrice(8999);
        product.setStockQuantity(20);
        productMybatisPlusMapper.insert(product);


        productDtoMapping.setProSup(product.getId(), supplier1.getId());
        productDtoMapping.setProSup(product.getId(), supplier2.getId());


        Product product2=new Product();
        product2.setName("IPhone 14 pro max");
        product2.setPrice(10000);
        product2.setStockQuantity(120);
        productMybatisPlusMapper.insert(product2);


        productDtoMapping.setProSup(product2.getId(), supplier2.getId());

    }


    //一对多查询
    @Test
    public void testFindProduct(){
        List<ProductDto> result = productDtoService.findProductSupplier(5);
        assertNotNull(result);
    }
    //添加测试
    @Test
    public void testAddProduct() throws ProductAdminException {
        Product product=new Product();
        product.setName("IPhone 18");
        product.setPrice(12000);
        product.setStockQuantity(10);
        productMybatisPlusMapper.insert(product);


        //验证添加的结果
        Long id = product.getId();
        Optional<Product> product2 = Optional.ofNullable(productMybatisPlusMapper.selectById(id));
        assertTrue(product2.isPresent());
        assertEquals("IPhone 18",product2.get().getName());

        //删除测试
        productMybatisPlusMapper.deleteById(id);
        assertNull(productMybatisPlusMapper.selectById(id));
    }


    @Test
    public void testFindProduct1(){
        Map<String,Object> condition=new HashMap<>();

        //添加动态查询条件
        condition.put("price",10000F);
        List<Product> result =productService.findProduct(condition);
        assertEquals(1,result.size());
        condition.put("name","IPhone 14 pro");
        List<Product> result1 =productService.findProduct(condition);
        assertEquals(1,result.size());
    }


}
