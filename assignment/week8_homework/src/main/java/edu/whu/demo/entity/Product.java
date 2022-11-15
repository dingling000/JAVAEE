package edu.whu.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaxy
 */

@Data
@Entity
public class Product {

    //产品Id
    @Id
    @GeneratedValue
    Long id;

    //产品名称
    String name;

    //产品单价
    float price;

    //库存数量
    float stockQuantity;

    //产品类别
    String category;

    //产品类型
    String productType;

    //产品描述
    String description;

    //供应商
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    List<Supplier> supplierList=new ArrayList<>();

}
