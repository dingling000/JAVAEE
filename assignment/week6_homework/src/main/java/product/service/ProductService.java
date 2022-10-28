package product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.Dao.ProductMybatisPlusMapper;
import product.domain.Product;
import product.domain.ProductDto;
import product.exception.ProductAdminException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductMybatisPlusMapper productMybatisPlusMapper;

    //添加商品
    public Product addProduct(Product product) throws ProductAdminException{
        if(productMybatisPlusMapper.selectById(product.getId()) != null){
            throw new ProductAdminException("添加商品失败：商品已经存在");
        }
        else{
            productMybatisPlusMapper.insert(product);
            return product;
        }
    }

    //删除商品
    public void deleteProduct(long id) {
            productMybatisPlusMapper.deleteById(id);
        }


    //更新商品
    public void updateProduct(long id,Product product) throws ProductAdminException{
        if(productMybatisPlusMapper.selectById(id) == null){
            throw new ProductAdminException("更新商品失败：商品不存在");
        }
        else{
            productMybatisPlusMapper.updateById(product);
        }
    }

    //根据id查询商品
    public Product getProductById(long id){

            return productMybatisPlusMapper.selectById(id);

    }

    //查询商品

    public List<Product> findProduct(Map<String, Object> condition){
        LambdaQueryWrapper<Product> lqw=new LambdaQueryWrapper<>();
        if (condition.containsKey("name")) {
            lqw.like(Product::getName,condition.get("name"));
        }
        if (condition.containsKey("price")) {
            lqw.lt(Product::getPrice,condition.get("price"));
        }
        if (condition.containsKey("stockQuantity")) {
            lqw.gt(Product::getStockQuantity,condition.get("stockQuantity"));
        }
        List<Product> productList=productMybatisPlusMapper.selectList(lqw);
        return productList;
    }



}
