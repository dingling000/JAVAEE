package product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {

    //产品Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;
    //产品名称
    String name;
    //产品单价
    float price;
    //库存数量
    float stockQuantity;
    //String description;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Supplier> suppliers=new ArrayList<>();

    public Product(String name,float price,float stockQuantity){
        this.name=name;
        this.price=price;
        this.stockQuantity=stockQuantity;
    }

}
