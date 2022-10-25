package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import product.Dao.ProductDao;
import product.entity.Product;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public Product getProductById(long id){
        return productDao.getReferenceById(id);
    }

    public List<Product> getProductByName(String name){
            Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (name!="") {
                predicateList.add(criteriaBuilder.equal(root.get("name"),name));
            }

            Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
            return criteriaBuilder.and(predicates);
        };

        List<Product> result = productDao.findAll(specification);
        return result;
    }
    public Product addProduct(Product p){
        return productDao.saveAndFlush(p);
    }
    public void updateProduct(long id,Product p){
        productDao.save(p);
    }

    public void deleteProduct(long id){
        productDao.deleteById(id);
    }

    public List<Product> findProduct(long id){return productDao.findAllById(Collections.singleton(id));}

    public List<Product> findTodos(float price,float stockQuantity) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (price<50) {
                predicateList.add(criteriaBuilder.like(root.get("price"), "%" + price + "%"));
            }
            if ((stockQuantity>100)) {
                predicateList.add(criteriaBuilder.like(root.get("stockQuantity"), "%"+stockQuantity+"%"));
            }
            Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
            return criteriaBuilder.and(predicates);
        };

        List<Product> result = productDao.findAll(specification);
        return result;
    }

}
