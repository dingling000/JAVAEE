package product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.Dao.ProductDtoMapping;
import product.domain.ProductDto;
import product.domain.Supplier;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductDtoService {
    @Autowired
    ProductDtoMapping productDtoMapping;
    //根据商品id查询供应商信息
    public List<ProductDto> findProductSupplier(long id){
        List<ProductDto> result = productDtoMapping.findAllProduct(id);
        return result;
    }

    //根据商品id查询其所有供应商
    public List<Supplier> findSupplierByPro(long id){
        return productDtoMapping.findSuplierByProId(id);
    }

}
