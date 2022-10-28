package product.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "supplier")

public class Supplier {



    @TableId(type = IdType.AUTO)
    long id;

    String name;

    String phone;
}
