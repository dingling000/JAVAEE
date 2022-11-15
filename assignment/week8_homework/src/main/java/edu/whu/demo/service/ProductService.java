package edu.whu.demo.service;

import edu.whu.demo.dao.ProductRepository;
import edu.whu.demo.entity.Product;
import edu.whu.demo.exception.ProductAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    public Product addProduct(Product product) throws ProductAdminException {
        if (product.getId() != null) {
            if (productRepository.findById(product.getId()).isPresent()) {
                throw new ProductAdminException("添加商品失败：商品已经存在");
            }
        }
        return productRepository.save(product);
    }

    /**
     * 根据Id查找
     *
     * @param id
     * @return
     */
    public Product getProduct(long id) {
        return productRepository.findById(id).get();
    }

    /**
     * 根据条件分页查找
     *
     * @param condition 查询条件
     * @param pageable  分页
     * @return 分页后的查询结果
     */
    public Page<Product> findProduct(@NotNull Map<String, Object> condition, @NotNull Pageable pageable) {
        return productRepository.findAll((root, query, cb) -> {
            List<Predicate> pList = new ArrayList<>();
            if (condition.containsKey("name")) {
                pList.add(cb.like(root.get("name"), "%" + condition.get("name") + "%"));
            }
            if (condition.containsKey("price")) {
                pList.add(cb.le(root.get("price"), (float) condition.get("price")));
            }
            if (condition.containsKey("stockQuantity")) {
                pList.add(cb.ge(root.get("stockQuantity"), (float) condition.get("stockQuantity")));
            }

            if (condition.containsKey("category")) {
                pList.add(cb.equal(root.get("category"), condition.get("category")));
            }
            if (condition.containsKey("productType")) {
                pList.add(cb.equal(root.get("productType"), condition.get("productType")));
            }
            if (condition.containsKey("supplierName")) {
                //根据供应商名称跨表查询，使用root.join('导航属性名').get('关联实体的属性')来表示
                pList.add(cb.equal(root.join("supplierList").get("name"), condition.get("supplierName")));
            }
            return cb.and(pList.toArray(new Predicate[pList.size()]));
        }, pageable);

    }

    /**
     * 更新商品信息
     *
     * @param id
     * @param product
     */
    public void updateProduct(long id, Product product) throws ProductAdminException {
        if (!productRepository.findById(id).isPresent()) {
            throw new ProductAdminException("修改商品失败：商品不存在");
        }
        productRepository.saveAndFlush(product);
    }

    /**
     * 删除商品
     *
     * @param id
     */
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }



}
