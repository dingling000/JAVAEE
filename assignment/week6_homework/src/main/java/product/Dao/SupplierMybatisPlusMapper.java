package product.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import product.domain.Supplier;

@Mapper
public interface SupplierMybatisPlusMapper extends BaseMapper<Supplier> {
}
