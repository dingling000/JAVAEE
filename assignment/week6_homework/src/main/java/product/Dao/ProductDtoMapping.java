package product.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import product.domain.Product;
import product.domain.ProductDto;
import product.domain.Supplier;

import java.util.List;

@Mapper
public interface ProductDtoMapping extends BaseMapper<Product>{

    @Insert("INSERT INTO pro_sup (pro_id,sup_id) VALUES (#{pro_id},#{sup_id})")
    void setProSup(long pro_id, long sup_id);

    @Select("SELECT supplier.* FROM supplier,pro_sup WHERE pro_sup.pro_id = #{id} AND pro_sup.sup_id=supplier.id")
    List<Supplier> findSuplierByProId(long id);

    @Select("SELECT * FROM product where id = #{id}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "suppliers", column = "id",
                    many = @Many(select = "product.Dao.ProductDtoMapping.findSuplierByProId"))
    })
    List<ProductDto> findAllProduct(long id);
}
