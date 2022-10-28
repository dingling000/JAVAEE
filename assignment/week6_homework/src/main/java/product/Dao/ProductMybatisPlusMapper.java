package product.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import product.domain.Product;
@Mapper
public interface ProductMybatisPlusMapper extends BaseMapper<Product> {
}
