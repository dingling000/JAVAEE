package product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "product")

public class Product {

    //产品Id

    @TableId(type = IdType.AUTO)

    long Id;
    //产品名称
    String name;
    //产品单价
    float price;
    //库存数量
    float stockQuantity;
    //String description;


}
