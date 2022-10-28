package product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
@Data
@TableName(value = "pro_sup")

public class ProductDto extends Product{

    List<Supplier> suppliers;


}
